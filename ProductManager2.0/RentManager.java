
import java.util.ArrayList;
import java.util.List;

public class RentManager {

	public static int incomeSummarizer(List<Buyable> products) {
		int income = 0;
		for (Buyable product: products) {
			income += product.getPrice();
		}
		return income;
	}
	
	public static Person createPersonObjects(String firstName, String lastName, Gender gender, int salary) 
	{
		Person onePerson = new Person();
		onePerson.setFirstName(firstName);
		onePerson.setLastName(lastName);
		onePerson.setGender(gender);
		onePerson.setSalary(salary);
		return onePerson;
	}
	
	public static void main(String[] args) {
		
		Person[] defaultActors = new Person[11];
		
		defaultActors[0] = createPersonObjects("Harrison", "Ford", Gender.MALE, 500000);
		Person harrisonFord = defaultActors[0];
		defaultActors[1] = createPersonObjects("Mark", "Hamill", Gender.MALE, 300000);
		Person markHamill = defaultActors[1];
		defaultActors[2] = createPersonObjects("Carrie", "Fisher", Gender.FEMALE, 300000);
		Person carrieFisher = defaultActors[2];
		defaultActors[3] = createPersonObjects("Karen", "Allen", Gender.FEMALE, 200000);
		Person karenAllen = defaultActors[3];
		defaultActors[4] = createPersonObjects("Emily", "Bronte", Gender.FEMALE, 80000);
		Person emilyBronte = defaultActors[4];
		defaultActors[5] = createPersonObjects("Robert Cecil", "Martin", Gender.MALE, 180000);
		Person robertMartin = defaultActors[5];
		defaultActors[6] = createPersonObjects("Chris", "Sawyer", Gender.MALE, 220000);
		Person chrisSawyer = defaultActors[6];
		defaultActors[7] = createPersonObjects("Sid", "Meyer", Gender.MALE, 300000);
		Person sidMeyer = defaultActors[7];
		defaultActors[8] = createPersonObjects("Will", "Wright", Gender.MALE, 500000);
		Person willWright = defaultActors[8];
		defaultActors[9] = createPersonObjects("John", "Newman", Gender.MALE, 70000);
		Person johnNewman = defaultActors[9];
		defaultActors[10] = createPersonObjects("Kate", "Johansson", Gender.FEMALE, 500000);
		Person kateJohansson = defaultActors[10];
		
		Product[] defaultProducts = new Product[6];
		
		defaultProducts[0] = new Movie("Star Wars", johnNewman);
		Movie starWars = (Movie) defaultProducts[0];
		starWars.setGenre(Genre.SCI_FI);
		starWars.setDuration(150l);
		starWars.setRate(5.0);
		List<Person> starWarsCast = new ArrayList<>();
		starWarsCast.add(harrisonFord);
		starWarsCast.add(markHamill);
		starWarsCast.add(carrieFisher);
		starWars.setCast(starWarsCast);
		starWars.setPrice(120);

		defaultProducts[1] = new Movie("Indiana Jones", kateJohansson);
		Movie indianaJones = (Movie) defaultProducts[1];
		indianaJones.setGenre(Genre.ACTION);
		indianaJones.setDuration(180l);
		indianaJones.setRate(4.9);
		List<Person> indianaJonesCast = new ArrayList<>();
		indianaJonesCast.add(harrisonFord);
		indianaJonesCast.add(karenAllen);
		indianaJones.setCast(indianaJonesCast);
		indianaJones.setPrice(100);
		
		defaultProducts[2] = new Game("Civilization V", johnNewman);
		Game civilization = (Game) defaultProducts[2];
		civilization.setPreOrdered(true);
		List<Person> civilizationStaff = new ArrayList<>();
		civilizationStaff.add(sidMeyer);
		civilizationStaff.add(robertMartin);
		civilization.setStaff(civilizationStaff);
		civilization.setPrice(150);
		
		defaultProducts[3] = new Game("The Sims", kateJohansson);
		Game theSims = (Game) defaultProducts[3];
		theSims.setPreOrdered(false);
		List<Person> theSimsStaff = new ArrayList<>();
		theSimsStaff.add(willWright);
		theSimsStaff.add(chrisSawyer);
		theSims.setStaff(theSimsStaff);
		theSims.setPrice(170);
	
		defaultProducts[4] = new Book("Wuthering Heights", kateJohansson);
		Book wutheringHeights = (Book) defaultProducts[4];
		wutheringHeights.setAuthor(emilyBronte);
	
		defaultProducts[5] = new Book("Clean Code", johnNewman);
		Book cleanCode = (Book) defaultProducts[5];
		cleanCode.setAuthor(robertMartin);

		// Printing out the investment of the products:
		
		System.out.println("Investment of Star Wars: " + starWars.getInvestment());
		System.out.println("Investment of Indiana Jones: " + indianaJones.getInvestment());
		System.out.println("Investment of Civilization: " + civilization.getInvestment());
		System.out.println("Investment of The Sims: " + theSims.getInvestment());														
		System.out.println("Investment of Wuthering Heights: " + wutheringHeights.getInvestment());
		System.out.println("Investment of Clean Code: " + cleanCode.getInvestment());
		
		List<Buyable> buyableProducts = new ArrayList<>();
		buyableProducts.add(starWars);
		buyableProducts.add(indianaJones);
		buyableProducts.add(civilization);
		buyableProducts.add(theSims);
		System.out.println("Total preferred income: " + incomeSummarizer(buyableProducts));
	}
}