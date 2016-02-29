import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectCreator {
	
	public static Person createPersonObject(String firstName, String lastName, Gender gender, int salary) {
		Person onePerson = new Person();
		onePerson.setFirstName(firstName);
		onePerson.setLastName(lastName);
		onePerson.setGender(gender);
		onePerson.setSalary(salary);
		return onePerson;
	}
	
	public static Movie createMovieObject(
			String title, Person customer, Genre genre, Long duration, double rate, Person[] cast, int price) {
		Movie oneMovie = new Movie(title, customer);
		oneMovie.setGenre(genre);
		oneMovie.setDuration(duration);
		oneMovie.setRate(rate);
		oneMovie.setCast(Arrays.asList(cast));
		oneMovie.setPrice(price);
		return oneMovie;
	}
	
	public static Book createBookObject(String title, Person customer, Person author) {
		Book oneBook = new Book(title, customer);
		oneBook.setAuthor(author);
		return oneBook;
	}
	
	public static Game createGameObject(String title, Person customer, boolean preOrdered, Person[] staff, int price) {
		Game oneGame = new Game(title, customer);
		oneGame.setPreOrdered(true);
		oneGame.setStaff(Arrays.asList(staff));
		oneGame.setPrice(150);
		return oneGame;
	}
	
	public static List<Object> initObjects() {
		List<Object> objects = new ArrayList<>();
		List<Person> persons = new ArrayList<>();
		List<Product> products = new ArrayList<>();
		
		persons.add(createPersonObject("Harrison", "Ford", Gender.MALE, 500000));
		Person harrisonFord = persons.get(0);
		persons.add(createPersonObject("Mark", "Hamill", Gender.MALE, 300000));
		Person markHamill = persons.get(1);
		persons.add(createPersonObject("Carrie", "Fisher", Gender.FEMALE, 300000));
		Person carrieFisher = persons.get(2);
		persons.add(createPersonObject("Karen", "Allen", Gender.FEMALE, 200000));
		Person karenAllen = persons.get(3);
		persons.add(createPersonObject("Emily", "Bronte", Gender.FEMALE, 80000));
		Person emilyBronte = persons.get(4);
		persons.add(createPersonObject("Robert Cecil", "Martin", Gender.MALE, 180000));
		Person robertMartin = persons.get(5);
		persons.add(createPersonObject("Chris", "Sawyer", Gender.MALE, 220000));
		Person chrisSawyer = persons.get(6);
		persons.add(createPersonObject("Sid", "Meyer", Gender.MALE, 300000));
		Person sidMeyer = persons.get(7);
		persons.add(createPersonObject("Will", "Wright", Gender.MALE, 500000));
		Person willWright = persons.get(8);
		persons.add(createPersonObject("John", "Newman", Gender.MALE, 70000));
		Person johnNewman = persons.get(9);
		persons.add(createPersonObject("Kate", "Johansson", Gender.FEMALE, 500000));
		Person kateJohansson = persons.get(10);
		
		Person[] starWarsCast = {harrisonFord, markHamill, carrieFisher};
		products.add(createMovieObject("Star Wars", johnNewman, Genre.SCI_FI, 150l, 5.0, starWarsCast, 120));
		
		Person[] indiJonesCast = {harrisonFord, karenAllen};
		products.add(createMovieObject("Indiana Jones", kateJohansson, Genre.ACTION, 180l, 4.9, indiJonesCast, 100));
		
		Person[] civStaff = {sidMeyer, robertMartin};
		products.add(createGameObject("Civilization V", johnNewman, true, civStaff, 150));
		
		Person[] simsStaff = {willWright, chrisSawyer};
		products.add(createGameObject("The Sims", kateJohansson, false, simsStaff, 170));
		
		products.add(createBookObject("Wuthering Heights", kateJohansson, emilyBronte));
	
		products.add(createBookObject("Clean Code", johnNewman, robertMartin));
		
		objects.addAll(persons);
		objects.addAll(products);
		
		return objects;
	}
}