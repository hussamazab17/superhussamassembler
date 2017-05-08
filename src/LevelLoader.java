
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {
	
	public static Level getLevel(String fileName) throws Exception {
		return getLevel(new File(fileName));
	}
	
	public static Level getLevel(File file) throws Exception {		
		Scanner scan = new Scanner(file);
		
		ArrayList<char[]> arr = new ArrayList<>();
		
		while(scan.hasNextLine()) {
			arr.add(scan.nextLine().toCharArray());
		}
		
		String name = file.getName().split("\\.")[0];
		
		char[][] carr = arr.toArray(new char[arr.size()][arr.get(0).length]);
		
		return new Level(name, carr);
	}

}
