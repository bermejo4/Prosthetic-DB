package db.inteface;

import java.util.List;

import pojos.Role;
import pojos.User;

public interface UserManagerInterface {
	public void connect();
	public void disconnect();
	public void createUser(User user);
	public User checkPassword(User user);
	Role getRole(int id);
	void createRole(Role role);
	List<Role> getRoles();
	public void updateUser(User user, int num);
	

}
