package db.inteface;

import java.util.List;

import pojos.Role;
import pojos.User;

public interface UserManagerInterface {
	public void connect();

	public void disconnect();

	public void createUser(User user);

	public User checkPassword(User user);

	public Role getRole(int id);

	public void createRole(Role role);

	List<Role> getRoles();

	public void updateUser(String username, byte[] password, int num);

	public void deleteUser(User user);
	public User getUserByTelephone(String telephone);

}
