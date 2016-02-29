# Java_ProductManager3.0_Week11A
Practice to use serialization, deserialization, sockets, networking and IO streams (such as file and object streams) in Java.

In this task I had to upgrade my Product and Buyable handler application, using serialization and networking. 
My task included to create a new class, called ObjectServer, what have to be able to run as a separated application, so it has to have a main method. This class has to be able to send and receive objects over TCP, working as a server.
The RentManager and the ObjectServer are expected to communicate with each other using sockets (they should run in separated JVMs). ObjectServer has to be able to save given data to a file and load data from file, depends on its mode property. 
I also had to modify the Person class and every 'Product' class, because they are needed to be able to read/write with ObjectInputStream/ObjectOutputStream. I also had to create an enum which contains 3 values: GET, PUT and EXIT. Any of these values can be sent to the server which can process these "commands" and switches its mode: GET means the server should read the file which contains the previously stored data and send it to the caller. PUT means the server should be switched to "store" mode, so the data which is sent to the server after PUT command should be stored in a file. EXIT means the server should shut down and the sockets are needed to be closed.

The UML diagram of the ProductManager application can be found in UML_ProductManager.jpg and about the ObjectServer application it can be found in UML_ObjectServer.jpg.
