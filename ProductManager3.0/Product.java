import java.io.Serializable;

public abstract class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	String title;
	Person person;
	
	public Product(String title, Person person) {
		id = IdGenerator.generate(this);
		this.title = title;
		this.person = person;		
	}
	
	public String getTitle() {
		return title;
	}
	public Person getPerson() {
		return person;
	}
	public abstract long getInvestment();
}
