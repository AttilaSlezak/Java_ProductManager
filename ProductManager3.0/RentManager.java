
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RentManager {

	private static int TCP = 5000;
	private static String IP = "127.0.0.1";
	private static Socket socket = null;
	private static ObjectInputStream objInStreamSocket;
	private static ObjectOutputStream objOutStreamSocket;	
	private static List<Object> serverCommands = new ArrayList<>();
	private static ServerMode ioMode = ServerMode.SAVE; 
	
	public static int incomeSummarizer(List<Buyable> products) {
		int income = 0;
		for (Buyable product: products) {
			income += product.getPrice();
		}
		return income;
	} 
	
	public static Person createPersonObject(String firstName, String lastName, Gender gender, int salary) {
		Person onePerson = new Person();
		onePerson.setFirstName(firstName);
		onePerson.setLastName(lastName);
		onePerson.setGender(gender);
		onePerson.setSalary(salary);
		return onePerson;
	}
	
	public static List<Buyable> createBuyableList(Buyable[] buyableProducts) {
		List<Buyable> buyableList = new ArrayList<>();
		for (Buyable buyable : buyableProducts) {
			buyableList.add(buyable);
		}
		return buyableList;
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
	
	public static Game createGameObject(String title, Person customer, boolean preOrdered, Person[] staff, int price) {
		Game oneGame = new Game(title, customer);
		oneGame.setPreOrdered(true);
		oneGame.setStaff(Arrays.asList(staff));
		oneGame.setPrice(150);
		return oneGame;
	}
		
	public static List<Object> load(String path) {
		List<Object> objIn = new ArrayList<>();
		Object oneObj;
		try {
			FileInputStream fileStream = new FileInputStream(path);
			ObjectInputStream os = new ObjectInputStream(fileStream);
			while (fileStream.available() != 0) {
				oneObj = (Object) os.readObject();
				objIn.add(oneObj);
			}
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
		   e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objIn;
	}
		
	public static void save(Object[] objOut, String path) {
		try {
			FileOutputStream fileStream = new FileOutputStream(path);
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			for (Object oneObj : objOut) {
				os.writeObject(oneObj);
			}
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveServer(List<Object> objOut) {
		int loops = 0;
		try {			
			for (Object oneObj : objOut) {
				objOutStreamSocket.writeObject(oneObj);
				loops++;
				
				if (oneObj == Command.GET) {
					ioMode = ServerMode.LOAD;
					break;
				} else if (oneObj == Command.EXIT) {
					socket.close();
					objInStreamSocket.close();
					objOutStreamSocket.close();
					socket = null;
					break;
				}
			}
			for (int i = 0; i < loops; i++) {
				serverCommands.remove(0);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Object> loadServer() {
		List<Object> objIn = new ArrayList<>();
		Object oneObj;
		try {
			System.out.println("Itt még van");
			while ((oneObj = objInStreamSocket.readObject()) != null) {
				System.out.println(oneObj);
				objIn.add(oneObj);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return objIn;
	}

	public static List<Object> serverIOSwitch() {
		List<Object> objIn = new ArrayList<>();
		try {
			if (socket == null) {
				socket = new Socket(IP, TCP);			
				objOutStreamSocket = new ObjectOutputStream(socket.getOutputStream());
				objInStreamSocket = new ObjectInputStream(socket.getInputStream());
			}
			while (true) {
				if (serverCommands.isEmpty() && ioMode != ServerMode.LOAD) {
					break;
				} else if (ioMode == ServerMode.LOAD) {
					System.out.println("Itt járok most.");
					objIn = loadServer();
					ioMode = ServerMode.SAVE;
				} else if (serverCommands.get(0) instanceof Command) {
					saveServer(serverCommands);
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return objIn;
	}
	
	public static void main(String[] args) {
		
		Person[] defaultPersons = new Person[11];
		
		defaultPersons[0] = createPersonObject("Harrison", "Ford", Gender.MALE, 500000);
		Person harrisonFord = defaultPersons[0];
		defaultPersons[1] = createPersonObject("Mark", "Hamill", Gender.MALE, 300000);
		Person markHamill = defaultPersons[1];
		defaultPersons[2] = createPersonObject("Carrie", "Fisher", Gender.FEMALE, 300000);
		Person carrieFisher = defaultPersons[2];
		defaultPersons[3] = createPersonObject("Karen", "Allen", Gender.FEMALE, 200000);
		Person karenAllen = defaultPersons[3];
		defaultPersons[4] = createPersonObject("Emily", "Bronte", Gender.FEMALE, 80000);
		Person emilyBronte = defaultPersons[4];
		defaultPersons[5] = createPersonObject("Robert Cecil", "Martin", Gender.MALE, 180000);
		Person robertMartin = defaultPersons[5];
		defaultPersons[6] = createPersonObject("Chris", "Sawyer", Gender.MALE, 220000);
		Person chrisSawyer = defaultPersons[6];
		defaultPersons[7] = createPersonObject("Sid", "Meyer", Gender.MALE, 300000);
		Person sidMeyer = defaultPersons[7];
		defaultPersons[8] = createPersonObject("Will", "Wright", Gender.MALE, 500000);
		Person willWright = defaultPersons[8];
		defaultPersons[9] = createPersonObject("John", "Newman", Gender.MALE, 70000);
		Person johnNewman = defaultPersons[9];
		defaultPersons[10] = createPersonObject("Kate", "Johansson", Gender.FEMALE, 500000);
		Person kateJohansson = defaultPersons[10];
		
		Product[] defaultProducts = new Product[6];
		
		Person[] starWarsCast = {harrisonFord, markHamill, carrieFisher};
		defaultProducts[0] = createMovieObject("Star Wars", johnNewman, Genre.SCI_FI, 150l, 5.0, starWarsCast, 120);
		Movie starWars = (Movie) defaultProducts[0];
		
		Person[] indiJonesCast = {harrisonFord, karenAllen};
		defaultProducts[1] = createMovieObject("Indiana Jones", kateJohansson, Genre.ACTION, 180l, 4.9, indiJonesCast, 100);
		Movie indianaJones = (Movie) defaultProducts[1];
		
		Person[] civStaff = {sidMeyer, robertMartin};
		defaultProducts[2] = createGameObject("Civilization V", johnNewman, true, civStaff, 150);
		Game civilization = (Game) defaultProducts[2];
		
		Person[] simsStaff = {willWright, chrisSawyer};
		defaultProducts[3] = createGameObject("The Sims", kateJohansson, false, simsStaff, 170);
		Game theSims = (Game) defaultProducts[3];
		
		defaultProducts[4] = new Book("Wuthering Heights", kateJohansson);
		Book wutheringHeights = (Book) defaultProducts[4];
		wutheringHeights.setAuthor(emilyBronte);
	
		defaultProducts[5] = new Book("Clean Code", johnNewman);
		Book cleanCode = (Book) defaultProducts[5];
		cleanCode.setAuthor(robertMartin);
		
		for (Product oneProduct : defaultProducts) {
			System.out.println("Investment of " + oneProduct.getTitle() + ": " + oneProduct.getInvestment());
		}
		
		Buyable[] buyableProducts = {starWars, indianaJones, civilization, theSims};
		System.out.println("Total preferred income: " + incomeSummarizer(createBuyableList(buyableProducts)));
		
		String productPath = "product.ser";
		String personPath = "person.ser";
		save(defaultProducts, productPath);
		save(defaultPersons, personPath);
		List<Object> newObjList = load(productPath);
		newObjList.addAll(load(personPath));
		List<Product> newProducts = new ArrayList<>();
		List<Person> newPersons = new ArrayList<>();
		for (Object oneObj : newObjList) {
			if (oneObj instanceof Product) {
				newProducts.add((Product)oneObj);
			} else if (oneObj instanceof Person) {
			    newPersons.add((Person)oneObj);
			}
		}
		for (Product oneProduct : newProducts) {
			System.out.println(oneProduct.getTitle() + ": " + oneProduct.getInvestment());
		}
		for (Person onePerson : newPersons) {
			System.out.println(onePerson.firstName + " " + onePerson.lastName);
		}
		serverCommands.add(Command.PUT);
		serverCommands.addAll(newProducts);
		serverCommands.add(Command.GET);
		List<Object> dataFromServer = serverIOSwitch();
		System.out.println(dataFromServer.size());
		serverCommands.add(Command.GET);
		serverCommands.add(Command.PUT);
		serverCommands.addAll(newPersons);
		serverCommands.add(Command.EXIT);
		dataFromServer = serverIOSwitch();
		System.out.println(dataFromServer.size());
	}
}