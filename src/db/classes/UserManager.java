package db.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import pojos.Role;
import pojos.User;
import ui.InputFlow;


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
	public User getUserByTelephone(String telephone) {
		Query q = em.createNativeQuery("SELECT username, password, role_id FROM users WHERE username =?", User.class);
		q.setParameter(3,telephone);
		User user = (User) q.getSingleResult();
		return user;
		
	}
	
	@Override
	public void updateUser(String username, byte[] password, int num) {
		try {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Role patientRole = new Role("patient");
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ?", User.class);
		q.setParameter(1,username);
		User user = (User) q.getSingleResult();
				switch(num) {
					case 1: 
						String newTelephone = InputFlow.takeTelephone(reader, "Introduce your new phone number:");
						em.getTransaction().begin();
						user.setUsername(newTelephone);
						em.getTransaction().commit();
						break;//If you want to modify only your username(telephone)
					case 2: 
						byte[] newPassword = InputFlow.takePasswordAndHashIt(reader, "Introduce the new password:");
						em.getTransaction().begin();
						user.setPassword(newPassword);
						em.getTransaction().commit();
						break;
					case 3: //To modify both of them
						String newTel = InputFlow.takeTelephone(reader, "Introduce your new phone number:");
						em.getTransaction().begin();
						user.setUsername(newTel);
						em.getTransaction().commit();
						byte[] newPas = InputFlow.takePasswordAndHashIt(reader, "Introduce the new password:");
						em.getTransaction().begin();
						user.setPassword(newPas);
						em.getTransaction().commit();
						break;
					}
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		em.close();
	}

	
	
	public void deleteUser(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
		
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
