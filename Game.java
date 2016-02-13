import java.util.List;

public class Game extends Product implements Buyable {

	boolean preOrdered;
	List<Person> staff;
	int price;
	
	public Game(String title, Person person) {
		this.title = title;
		this.person = person;
	}
	
	public boolean isPreOrdered() {
		return preOrdered;
	}

	public void setPreOrdered(boolean preOrdered) {
		this.preOrdered = preOrdered;
	}

	public List<Person> getStaff() {
		return staff;
	}

	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public int getPrice() {
		if (preOrdered) {
			return (int) (0.8 * price);
		} else {
			return price;
		}
	}

	@Override
	public long getInvestment() {
		int result = 0;
		for (Person person: staff) {
			result += person.getSalary();
		}
		return result;
	}
}
