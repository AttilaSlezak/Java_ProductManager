
public class Book extends Product {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Person author;
	
	public Book(String title, Person person) {
		super(title, person);
	}
	
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	@Override
	public long getInvestment() {
		return author.getSalary();
	}

}
