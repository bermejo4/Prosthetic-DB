package db.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.inteface.UserManagerInterface;
import pojos.Doctor;
import pojos.Patient;
import pojos.User;
import pojos.Role;


public class UserManager implements UserManagerInterface {

	private EntityManager em;
	
	@Override
	public void connect() {
		em = Persistence.createEntityManagerFactory("Prosthetic-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() {
		em.close();
	}

	@Override
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
		}
	

	@Override
	public Role getRole(int id) {
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		Role role = (Role) q.getSingleResult();
		return role;
	}

	@Override
	public List<Role> getRoles() {
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}
	
	@Override
	public void updateUser(User user, int num) {
		em = Persistence.createEntityManagerFactory("Prosthetic-provider").createEntityManager();
		
		em.getTransaction().begin();
		switch(num) {
			case 1: user.setUsername(user.getUsername()); //If you want to modify only your username(telephone)
			case 2: user.setPassword(user.getPassword());
			default: //To modify both of them
				user.setUsername(user.getUsername());
				user.setPassword(user.getPassword());
			}
		em.getTransaction().commit();
		em.close();
	}
	
	// its going to be a real user if the password is checked
	public void deleteUser(User user, int num) {
		
		
	}
	
	@Override
	public User checkPassword(User userps) {
		User user = null;
		try {
			// Create the query
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, userps.getUsername());
			q.setParameter(2, userps.getPassword());
			user = (User) q.getSingleResult();
		} catch (NoResultException nre) {
			// This is what happens when no result is retrieved
			return null;
		}
		return user;
	}

}
