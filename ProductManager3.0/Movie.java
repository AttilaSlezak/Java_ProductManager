import java.util.List;
import java.util.ArrayList;

class Movie extends Product implements Buyable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Genre genre;
	Long duration;
	Double rate;
	List<Person> cast;
	int price;
	//ArrayList<Person> cast;
	
	public Movie(String title, Person person) {
		super(title, person);
	}
	
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Long getDuration() {
		return duration;
	}
	public void setDuration(Long duration) {
		this.duration = duration;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public List<Person> getCast() {
		return cast;
	}
	public void setCast(List<Person> cast) {
		this.cast = new ArrayList<Person>(cast);
	}	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public long getInvestment() {
		int result = 0;
		for (Person person: cast) {
			result += person.getSalary();
		}
		return result;
	}
}