package db.inteface;

import pojos.User;

public interface UserManagerInterface {
	public void connect();
	public void disconnect();
	public void createUser(User user);
	public int checkPassword(String username, byte[] password, String table, String name_id);
	

}
