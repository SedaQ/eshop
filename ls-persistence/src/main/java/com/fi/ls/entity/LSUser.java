package com.fi.ls.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Pavel Šeda (441048)
 *
 */
@Entity
@Table(name = "lsuser")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQuery(name = "LSUser.findAll", query = "SELECT u FROM LSUser u")
public class LSUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_user")
	protected Long id;

	@NotNull
	@Pattern(regexp = ".+@.+\\....?")
	@Column(unique = true)
	private String email;

	@NotNull
	private String passwordHash;

	public LSUser() {
	}

	public Long getId() {
		return id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LSUser))
			return false;
		LSUser other = (LSUser) obj;
		if (email == null) {
			if (other.getEmail() != null)
				return false;
		} else if (!email.equals(other.getEmail()))
			return false;
		if (passwordHash == null) {
			if (other.getPasswordHash() != null)
				return false;
		} else if (!passwordHash.equals(other.getEmail()))
			return false;
		return true;
	}

}