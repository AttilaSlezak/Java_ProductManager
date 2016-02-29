
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
	
	public static List<Buyable> listBuyables(List<Product> products) {
		List<Buyable> buyables = new ArrayList<>();
		for (Product oneProduct : products) {
			if (oneProduct instanceof Buyable) {
				buyables.add((Buyable)oneProduct);
			}
		}
		return buyables;
	}
	
	private static void printPersonList(List<Person> persons) {
		for (Person onePerson : persons) {
			System.out.println(onePerson.firstName + " " + onePerson.lastName);
		}
	}
	
	private static void printProductList(List<Product> products) {
		for (Product oneProduct : products) {
			System.out.println(oneProduct.getTitle() + ": " + oneProduct.getInvestment());
		}
	}
	
	private static List<Person> castPersonFromObj(List<Object> objIn) {
		List<Person> result = new ArrayList<>();
		for (Object oneObj : objIn) {
			if (oneObj instanceof Person) {
				result.add((Person)oneObj);
			} 
		}
		return result;
	}
	
	private static List<Product> castProductFromObj(List<Object> objIn) {
		List<Product> result = new ArrayList<>();
		for (Object oneObj : objIn) {
			if (oneObj instanceof Product) {
				result.add((Product)oneObj);
			}
		}	
		return result;
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
		
	public static void save(List<Object> objOut, String path) {
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
			while ((oneObj = objInStreamSocket.readObject()) != null) {
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
		
		List<Object> objects = ObjectCreator.initObjects();
		List<Person> persons = castPersonFromObj(objects);
		List<Product> products = castProductFromObj(objects);
		List<Buyable> buyableProducts = listBuyables(products);
		
		System.out.println("People's name:");
		printPersonList(persons);
		
		System.out.println("\nInvestment of the products:");
		for (Product oneProduct : products) {
			System.out.println("Investment of " + oneProduct.getTitle() + ": " + oneProduct.getInvestment());
		}
		
		System.out.println("Total preferred income: " + incomeSummarizer(buyableProducts));
		
		String productPath = "product.ser";
		String personPath = "person.ser";
		save(objects, productPath);
		List<Object> newObjList = load(productPath);
		newObjList.addAll(load(personPath));
		List<Product> newProducts = castProductFromObj(newObjList);
		List<Person> newPersons = castPersonFromObj(newObjList);
		
		System.out.println("\nGot data from file:");
		printProductList(newProducts);
		printPersonList(newPersons);
		
		serverCommands.add(Command.PUT);
		serverCommands.addAll(newProducts);
		serverCommands.addAll(newPersons);
		serverCommands.add(Command.GET);
		serverCommands.add(Command.EXIT);
		List<Object> dataFromServer = serverIOSwitch();
		System.out.println("\nGot data from the server:");
		printProductList(castProductFromObj(dataFromServer));
		printPersonList(castPersonFromObj(dataFromServer));
	}
}