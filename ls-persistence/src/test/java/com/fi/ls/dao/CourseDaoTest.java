package com.fi.ls.dao;

import com.fi.ls.entity.Course;
import com.fi.ls.enums.ProficiencyLevel;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lukas Daubner (410034)
 */
@ContextConfiguration(classes = com.fi.ls.context.PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourseDaoTest extends AbstractTestNGSpringContextTests {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CourseDao courseDao;

	Course c;

	@BeforeMethod
	public void beforeMethod() {
		c = new Course();
		c.setLanguage("eng");
		c.setName("English 101");
		c.setProficiencyLevel(ProficiencyLevel.A1);
	}

	@Test
	public void testCreate() {

		courseDao.create(c);

		Assert.assertNotNull(em.find(Course.class, c.getId()));
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testCreateNull() {

		courseDao.create(null);

		Assert.fail("Expected exception! Null parameter.");
	}

	@Test(expectedExceptions = JpaSystemException.class)
	public void testCreateNonUniqueName() {

		Course c1 = new Course();
		c1.setLanguage("eng");
		c1.setName("English 101");
		c1.setProficiencyLevel(ProficiencyLevel.A1);

		courseDao.create(c);
		courseDao.create(c1);

		Assert.fail("Expected exception! Two courses with identical names persisted.");
	}

	@Test(expectedExceptions = ConstraintViolationException.class)
	public void testCreateNullName() {

		c.setName(null);

		courseDao.create(c);

		Assert.fail("Expected exception! Name is null.");
	}

	@Test
	public void testFindById() {

		em.persist(c);

		Course found = courseDao.findById(c.getId());

		Assert.assertEquals(c, found);
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testFindByIdNull() {

		em.persist(c);

		Course found = courseDao.findById(null);

		Assert.fail("Expected exception! Null parameter.");
	}

	@Test
	public void testUpdate() {

		em.persist(c);
		em.detach(c);
		c.setLanguage("cmn");
		courseDao.update(c);

		Assert.assertEquals(c, em.find(Course.class, c.getId()));
	}

	@Test(expectedExceptions = DataAccessException.class)
	public void testUpdateNull() {

		em.persist(c);
		em.detach(c);
		c.setLanguage("cmn");
		courseDao.update(null);

		Assert.fail("Expected exception! Null parameter.");
	}

	@Test
	public void testRemove() {

		em.persist(c);
		courseDao.remove(c);

		Assert.assertNull(em.find(Course.class, c.getId()));
	}

	@Test(expectedExceptions = NullPointerException.class)
	public void testRemoveNull() {

		em.persist(c);
		courseDao.remove(null);

		Assert.fail("Expected exception! Null parameter.");
	}

	@Test
	public void testFindByName() {

		em.persist(c);
		Assert.assertNotNull(courseDao.findByName(c.getName()));
		Assert.assertEquals(courseDao.findByName(c.getName()).getName(), "English 101");

	}

	@Test
	public void testFindAll() {

		Course c1 = new Course();
		c1.setLanguage("qnp");
		c1.setName("unnamed");
		c1.setProficiencyLevel(ProficiencyLevel.C2);

		Course c2 = new Course();
		c2.setLanguage("zxx");
		c2.setName("N/A");
		c2.setProficiencyLevel(ProficiencyLevel.B1);

		em.persist(c);
		em.persist(c1);
		em.persist(c2);

		Set<Course> allCourses = courseDao.findAll();

		Assert.assertEquals(allCourses.size(), 3);
	}
}
