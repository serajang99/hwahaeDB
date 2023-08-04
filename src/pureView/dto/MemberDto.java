package pureView.dto;

public class MemberDto {

	private String id, name, passwd, skintype;
	private int age;

	public MemberDto(String id, String name, String passwd, String skintype, int age) {
		super();
		this.id = id;
		this.name = name;
		this.passwd = passwd;
		this.skintype = skintype;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getSkintype() {
		return skintype;
	}

	public void setSkintype(String skintype) {
		this.skintype = skintype;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", passwd=" + passwd + ", skintype=" + skintype + ", age=" + age;
	}

}
