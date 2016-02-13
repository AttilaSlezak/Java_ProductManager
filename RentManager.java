
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
	
	public static void main(String[] args) {
		Person[] defaultActors = new Person[11];
		
		defaultActors[0] = new Person();
		Person harrisonFord = defaultActors[0];
		harrisonFord.setFirstName("Harrison");
		harrisonFord.setLastName("Ford");
		harrisonFord.setGender(Gender.MALE);
		harrisonFord.setSalary(500000);
		
		defaultActors[1] = new Person();
		Person markHamill = defaultActors[1];
		markHamill.setFirstName("Mark");
		markHamill.setLastName("Hamill");
		markHamill.setGender(Gender.MALE);
		markHamill.setSalary(300000);
		
		defaultActors[2] = new Person();
		Person carrieFisher = defaultActors[2];
		carrieFisher.setFirstName("Carrie");
		carrieFisher.setLastName("Fisher");
		carrieFisher.setGender(Gender.FEMALE);
		carrieFisher.setSalary(300000);
		
		defaultActors[3] = new Person();
		Person karenAllen = defaultActors[3];
		karenAllen.setFirstName("Karen");
		karenAllen.setLastName("Allen");
		karenAllen.setGender(Gender.FEMALE);
		karenAllen.setSalary(200000);
		
		defaultActors[4] = new Person();
		Person emilyBronte = defaultActors[4];
		emilyBronte.setFirstName("Emily");
		emilyBronte.setLastName("Bronte");
		emilyBronte.setGender(Gender.FEMALE);
		emilyBronte.setSalary(80000);
		
		defaultActors[5] = new Person();
		Person robertMartin = defaultActors[5];
		robertMartin.setFirstName("Robert Cecil");
		robertMartin.setLastName("Martin");
		robertMartin.setGender(Gender.MALE);
		robertMartin.setSalary(180000);
		
		defaultActors[6] = new Person();
		Person chrisSawyer = defaultActors[6];
		chrisSawyer.setFirstName("Chris");
		chrisSawyer.setLastName("Sawyer");
		chrisSawyer.setGender(Gender.MALE);
		chrisSawyer.setSalary(220000);
		
		defaultActors[7] = new Person();
		Person sidMeyer = defaultActors[7];
		sidMeyer.setFirstName("Sid");
		sidMeyer.setLastName("Meyer");
		sidMeyer.setGender(Gender.MALE);
		sidMeyer.setSalary(300000);
		
		defaultActors[8] = new Person();
		Person willWright = defaultActors[8];
		willWright.setFirstName("Will");
		willWright.setLastName("Wright");
		willWright.setGender(Gender.MALE);
		willWright.setSalary(280000);

		defaultActors[9] = new Person();
		Person johnNewman = defaultActors[9];
		johnNewman.setFirstName("John");
		johnNewman.setLastName("Newman");
		johnNewman.setGender(Gender.MALE);
		johnNewman.setSalary(70000);

		defaultActors[10] = new Person();
		Person kateJohansson = defaultActors[10];
		kateJohansson.setFirstName("Kate");
		kateJohansson.setLastName("Johansson");
		kateJohansson.setGender(Gender.FEMALE);
		kateJohansson.setSalary(80000);
		
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
