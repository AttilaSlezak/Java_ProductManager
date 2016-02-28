import java.util.List;
import java.util.ArrayList;

class Movie
{
	String title;
	Genre genre;
	Long duration;
	Double rate;
	List<Person> cast;
	//ArrayList<Person> cast;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String toXMLString() {
		String value = Tools.toXMLTag("title", this.title);
		value += Tools.toXMLTag("genre", this.genre.toString());
		value += Tools.toXMLTag("duration", this.duration.toString());
		value += Tools.toXMLTag("rate", this.rate.toString());
		String castAsXML = "";
		for (Person person: cast) {
			castAsXML += person.toXMLString();
		}
		value += Tools.toXMLTag("cast", castAsXML);
		return Tools.toXMLTag("Movie", value);
	}
}