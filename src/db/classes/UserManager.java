package db.classes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import db.inteface.UserManagerInterface;
import pojos.User;


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

	/*@Override
	public void createRole(Role role) {
		em.getTransaction().begin();
		em.persist(role);
		em.getTransaction().commit();
	}*/
/*
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
	*/

	@Override
	public int checkPassword(String telephone, byte[] password, String table, String name_id) {
		int num=0;
		try {
			/*// Create a MessageDigest
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();*/
			// Create the query
			Query q = em.createNativeQuery("SELECT "+name_id+" FROM "+table+" WHERE telephone = ? AND password = ?", User.class);
			q.setParameter(1, telephone);
			q.setParameter(2, "rojo");
			num = (Integer) q.getSingleResult();
			System.out.println("num:"+num);
		} catch (NoResultException nre) {
			// This is what happens when no result is retrieved
			return 0;
		}
		return num;
	}

}
