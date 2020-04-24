package db.inteface;

import pojos.User;

public interface UserManagerInterface {
	public void connect();
	public void disconnect();
	public void createUser(User user);
	public User checkPassword(String username, String password);
	

}
