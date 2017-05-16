package main;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Random;

public class Level {
	
	/*
	A = AIR
	B = GRASS
	C = DIRT
	D = LAVA
	E = ENEMY
	F = PLATFORM
	X = STAR
	*/
	
	private char arr[][];
	private String name;
	
	public Level(String name, char[][] arr) {
		this.name = name;
		this.arr = arr;
	}
	
	public void save() throws IOException {
		File f = new File(name + ".txt");
		
		if(!f.exists()) 
			f.createNewFile();
		
		f.setWritable(true);
		
		FileWriter fw = new FileWriter(f);
		
		for(Field field : fw.getClass().getFields()) {
			System.out.println(field.getName());
		}
		
		for(char[] carr : arr) {
			System.out.println(new String(carr));
			fw.append(new String(carr) + System.lineSeparator());
		}
		
		fw.flush();
		fw.close();
	}
	
}