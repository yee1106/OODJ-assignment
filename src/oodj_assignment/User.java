package oodj_assignment;

import java.util.Date;

public class User {

	private String ID;
	private String password;
	private String name;
  private Date login;
  private Date logout;
  private String mode;

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public Date getLogin() {
    return login;
  }

  public void setLogin(Date login) {
    this.login = login;
  }

  public Date getLogout() {
    return logout;
  }

  public void setLogout(Date logout) {
    this.logout = logout;
  }
  
	public User() {
	}

  public User(String ID,String name,Date login,Date logout,String mode) {
    this.ID = ID;
    this.name = name;
    this.login=login;
    this.logout=logout;
    this.mode=mode;
	}
  
	public User(String ID, String password, String name) {
		this.ID = ID;
		this.password = password;
		this.name = name;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

  @Override
  public String toString() {
    return "User{" + "ID=" + ID + ", name=" + name + ", login=" + login + ", logout=" + logout + ", mode=" + mode + '}';
  }


  
}

