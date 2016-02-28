import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {
	private static ServerMode mode;
	private static int TCP = 5000;
	private static String FILE_PATH = "objects.ser";
	private static ServerSocket sSocket;
	private static Socket socket;
	private static ObjectInputStream sInStream;
	private static ObjectOutputStream sOutStream;
	
	public static ServerMode getMode() {
		return mode;
	}

	public static void setMode(ServerMode mode) {
		ObjectServer.mode = mode;
	}
	
	public static List<Object> load() {
		List<Object> objIn = new ArrayList<>();
		Object oneObj;
		try {
			FileInputStream fileStream = new FileInputStream(FILE_PATH);
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
		
	public static void save(Object[] objOut) {
		try {
			FileOutputStream fileStream = new FileOutputStream(FILE_PATH);
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
	
	private static void startServer() {
		try {
			socket = sSocket.accept();
			sInStream = new ObjectInputStream(socket.getInputStream());
			sOutStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	private static boolean serverManager() {
		boolean shutDown = false;
		List<Object> objIn = new ArrayList<>();
		Object[] serverData;
		Object oneObj = null;
		while (!shutDown) {
			try {
				if (oneObj instanceof Command || (oneObj = sInStream.readObject()) instanceof Command) {
					Command command = (Command)oneObj;
					if (command == Command.GET) {
						setMode(ServerMode.LOAD);
						List<Object> objOut = load();
						for (Object oneObjOut : objOut) {
							sOutStream.writeObject(oneObjOut);
						}
						oneObj = null;
						sOutStream.writeObject(oneObj);
						System.out.println("Objects have been sended to the client succesfully.");
					} else if (command == Command.PUT) {
						setMode(ServerMode.SAVE);
						while (!((oneObj = sInStream.readObject()) instanceof Command)) {
							objIn.add(oneObj);
						}
						serverData = new Object[ objIn.size() ];
						objIn.toArray(serverData);
						save(serverData);
						System.out.println("Objects have been saved successfully.");
					} else {
						shutDown = true;
					}
				} else {
					System.out.println("Command is not identifiable!");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		}
		return shutDown;
	}
	
	private static void shutDown() {
		try {
			sSocket.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Server connection closed.");
	}
	
	public static void main(String[] args) throws IOException {
		sSocket = new ServerSocket(TCP);
		boolean shutDown = false;
		while (!shutDown) {
			startServer();
			shutDown = serverManager();
		}
		shutDown();
		System.exit(1);
	}
}