
public class IdGenerator {
	private static int numberOfMovie = 0;
	private static int numberOfGame = 0;
	private static int numberOfBook = 0;
	
	public static String generate(Product product) {
		if (product instanceof Movie) {
			return "MOV" + String.valueOf(++numberOfMovie);
		} else if (product instanceof Game)  {
			return "GAM" + String.valueOf(++numberOfGame);
		} else if (product instanceof Book) {
			return "BOO" + String.valueOf(++numberOfBook);
		}
		return "";
	}
}
