package com.fi.ls.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.dozer.Mapping;

/**
 *
 * @author Lukas Daubner (410034)
 */
@Entity
@Table(name = "lecturer")
@NamedQueries( {
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findAllLecturerLanguages", query = "SELECT lan FROM Language lan WHERE lan.lecturer.id = :lID")
} )
public class Lecturer extends LSUser {

	@NotNull
	@Column(unique = true)
	private String nickname;

	@NotNull
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	private String surname;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Language.class, mappedBy = "lecturer")
	@Column(name = "list_of_languages")
	@Mapping("listOfLanguages")
	private Set<Language> listOfLanguages = new HashSet<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@Column(name = "list_of_lectures")
	@Mapping("listOfLectures")
	private Set<Lecture> listOfLectures = new HashSet<>();

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void deleteLecture(Lecture lecture) {
		this.listOfLectures.remove(lecture);
	}

	public void deleteLectures(Set<Lecture> lectures) {
		lectures.forEach(lect -> listOfLectures.remove(lect));
	}

	public Set<Language> getListOfLanguages() {
		return Collections.unmodifiableSet(listOfLanguages);
	}
        
        public void setListOfLanguages(Set<Language> listOfLanguages) {
        this.listOfLanguages = listOfLanguages;
	}

	public Set<Lecture> getListOfLectures() {
		return Collections.unmodifiableSet(listOfLectures);
	}

        public void setListOfLectures(Set<Lecture> listOfLectures) {
            this.listOfLectures = listOfLectures;
        }

	public void addLanguage(Language lan) {
		listOfLanguages.add(lan);
		lan.setLecturer(this);
	}
        
        public void addLecture(Lecture l) {
		listOfLectures.add(l);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Lecturer))
			return false;
		Lecturer other = (Lecturer) obj;
		if (this.nickname == null) {
			if (other.getNickname() != null)
				return false;
		} else {
			if (!this.nickname.equals(other.getNickname()))
				return false;
		}
		if (this.firstName == null) {
			if (other.getFirstName() != null)
				return false;
		} else {
			if (!this.firstName.equals(other.getFirstName()))
				return false;
		}
		if (this.surname == null) {
			if (other.getSurname() != null)
				return false;
		} else {
			if (!this.surname.equals(other.getSurname()))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		return (nickname == null ? 0 : nickname.hashCode());
	}
}
