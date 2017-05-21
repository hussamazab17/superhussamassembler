package main;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Level {
	
	/*
	A = AIR
	B = GRASS
	C = DIRT
	D = LAVA
	E = ENEMY
	F = PLATFORM
	S = SPAWN
	X = STAR
	*/
	
	private char arr[][];
	private String name;
	
	public Level(String name, char[][] arr) {
		this.name = name;
		this.arr = arr;
	}
	
	public Level(String name) {
		this.name = name;
		this.arr = new char[5][10];
		
		char[] charr = new char[] {'A', 'B', 'C'};
		
		for(int y = 0; y < arr.length; y++) {
			for(int x = 0; x < arr[0].length; x++) {
				arr[y][x] = charr[(int) Math.random() * 3];
			}
		}
	}
	
}