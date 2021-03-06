package com.fi.ls.dao;

import com.fi.ls.entity.Language;
import com.fi.ls.entity.Lecturer;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Lukas Daubner (410034)
 */
public interface LecturerDao {
    
    	/**
	 * create new Lecturer in database 
	 * @param l specific Lecturer to be created
	 */
	public void create(Lecturer l);
	
	/**
	 * finds specific Lecturer by id
	 * @param id of a Lecturer that would be returned
	 * @return specific Lecturer by id
	 */
	public Lecturer findById(Long id);
	
	/**
	 * updates given Lecturer
	 * @param l Lecturer that has to be updated
	 * @return updated Lecturer
	 */
	public Lecturer update(Lecturer l);
	
	/**
	 * removes given Lecturer
	 * @param l Lecturer that has to be removed
	 */
	public void remove(Lecturer l);
	
	/**
	 * Returns all Lecturers
	 * @return List of Lecturers
	 */
	public Set<Lecturer> findAll();
        
        /**
	 * Returns all Languages of lecturer
         * @param l Lecturer who's languages are returned
	 * @return List of Languages
	 */
	public Set<Language> findAllLecturerLanguages(Lecturer l);
	
}
