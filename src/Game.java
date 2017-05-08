
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

public class Game {

    public static void main(String[] args) throws Exception {
        new Level().save();
    }

}

class Level {
	
	private char arr[][];
	private String name;
	
	public Level() {
		arr = new char[5][5];
		
		char[] str = {'A', 'B', 'C'};
		
		Random rand = new Random();
		
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 5; c++) {
				arr[r][c] = str[rand.nextInt(3)];
			}
		}
		
		name = "test";
	}
	
	public void save() throws IOException {
		File f = new File(name + ".txt");
		
		int i = 0;
		
		while(f.exists()) {
			f = new File(name + ++i + ".txt");
		}
		
		f.createNewFile();
		
		f.setWritable(true);
		
		FileWriter fw = new FileWriter(f);
		
		for(Field field : fw.getClass().getFields()) {
			System.out.println(field.getName());
		}
		
		for(char[] carr : arr) {
			System.out.println(new String(carr));
			for(char c : carr) {
				fw.write(c);
			}
		}
	}
	
}
