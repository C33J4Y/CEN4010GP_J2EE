package fau.schedule.vo;

public class StudentVo {

	private final int zno;
	private final String fname;
	private final String lname;
	private final String password;
	private final boolean isAdmin;

	public StudentVo(final int zno, final String fname, final String lname, final String password, final boolean isAdmin) {
		this.zno = zno;
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public StudentVo(final int zno, final String fname, final String lname, final String password) {
		this(zno, fname, lname, password, false);
	}

	public int getZno() {
		return zno;
	}

	public String getFName() {
		return fname;
	}

	public String getLName() {
		return lname;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

}
