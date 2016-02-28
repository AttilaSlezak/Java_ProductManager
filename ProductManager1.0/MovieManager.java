import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;

class MovieManager
{
	public static final String XML_PATH = "C:/DEV/Java_and_XML/movies.xml"; 
	public static void main(String[] args)
	{
		Person[] defaultActors = new Person[5];
		
		defaultActors[0] = new Person();
		Person kateWinslet = defaultActors[0];
		kateWinslet.setFirstName("Kate");
		kateWinslet.setLastName("Winslet");
		kateWinslet.setGender(Gender.FEMALE);
		kateWinslet.setHasOscar(true);
		kateWinslet.setHasGoldenGlobe(true);
		
		defaultActors[1] = new Person();
		Person leonardoDiCaprio = defaultActors[1];
		leonardoDiCaprio.setFirstName("Leonardo");
		leonardoDiCaprio.setLastName("Di Caprio");
		leonardoDiCaprio.setGender(Gender.MALE);
		leonardoDiCaprio.setHasOscar(false);
		leonardoDiCaprio.setHasGoldenGlobe(true);
		
		defaultActors[2] = new Person();
		Person keanuReeves = defaultActors[2];
		keanuReeves.setFirstName("Keanu");
		keanuReeves.setLastName("Reeves");
		keanuReeves.setGender(Gender.MALE);
		keanuReeves.setHasOscar(false);
		keanuReeves.setHasGoldenGlobe(false);
		
		defaultActors[3] = new Person();
		Person carrieAnneMoss = defaultActors[3];
		carrieAnneMoss.setFirstName("Carrie-Anne");
		carrieAnneMoss.setLastName("Moss");
		carrieAnneMoss.setGender(Gender.FEMALE);
		carrieAnneMoss.setHasOscar(false);
		carrieAnneMoss.setHasGoldenGlobe(false);
		
		defaultActors[4] = new Person();
		Person marionCotillard = defaultActors[4];
		marionCotillard.setFirstName("Marion");
		marionCotillard.setLastName("Cotillard");
		marionCotillard.setGender(Gender.FEMALE);
		marionCotillard.setHasOscar(true);
		marionCotillard.setHasGoldenGlobe(true);
		
		Movie[] defaultMovies = new Movie[3];
		
		defaultMovies[0] = new Movie();
		Movie titanic = defaultMovies[0];
		titanic.setTitle("Titanic");
		titanic.setGenre(Genre.ROMANTIC);
		titanic.setDuration(200l);
		titanic.setRate(4.8);
		List<Person> titanicCast = new ArrayList<>();
		titanicCast.add(leonardoDiCaprio);
		titanicCast.add(kateWinslet);
		titanic.setCast(titanicCast);
		
		defaultMovies[1] = new Movie();
		Movie theMatrix = defaultMovies[1];
		theMatrix.setTitle("The Matrix");
		theMatrix.setGenre(Genre.SCI_FI);
		theMatrix.setDuration(140l);
		theMatrix.setRate(5.0);
		List<Person> theMatrixCast = new ArrayList<>();
		theMatrixCast.add(keanuReeves);
		theMatrixCast.add(carrieAnneMoss);
		theMatrix.setCast(theMatrixCast);
		
		defaultMovies[2] = new Movie();
		Movie inception = defaultMovies[2];
		inception.setTitle("Inception");
		inception.setGenre(Genre.SCI_FI);
		inception.setDuration(160l);
		inception.setRate(5.0);
		List<Person> inceptionCast = new ArrayList<>();
		inceptionCast.add(leonardoDiCaprio);
		inceptionCast.add(marionCotillard);
		inception.setCast(inceptionCast);
		String result = "";
		for (int i = 0; i < defaultMovies.length; i++) {
			result += defaultMovies[i].toXMLString();
		}
		result = Tools.toXMLTag("movies", result);
		System.out.println(result);
		try {
			FileWriter xmlToFile = new FileWriter(XML_PATH);
			xmlToFile.write(result);
			xmlToFile.close();
		}
		catch (java.io.IOException ioError) {
			System.out.println("The creation of the xml file was unsuccessful!");
		}
	}
}