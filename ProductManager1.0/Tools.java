import java.util.HashMap;
import java.util.List;

class Tools
{
	public static HashMap<Person, Integer> countMoviesPerPerson(List<Movie> movies)
	{
		HashMap<Person, Integer> moviesPerPerson = new HashMap<Person, Integer>(); 
		for (Movie movie: movies) {
			for (Person actor: movie.getCast()) {
				if (moviesPerPerson.containsKey(actor)) {
					moviesPerPerson.put(actor, moviesPerPerson.get(actor) + 1);
				}
				else {
					moviesPerPerson.put(actor, 1);
				}
			}
		}			
		return moviesPerPerson;
	}
	
	public static String[] getMovieTitles(List<Movie> movies)
	{
		int numberOfMovies = movies.size();
		String[] titles = new String[numberOfMovies];
		for (int i = 0; i < numberOfMovies; i++) {
			titles[i] = movies.get(i).getTitle();
		}
		return titles;
	}
	
	public static String toXMLTag(String tagName, String value)
	{
		return "<" + tagName + ">" + value + "</" + tagName + ">";
	}
}