package ru.java2e;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Audio implements Runnable {

	@Override
	public void run() {
	try {
		Player p = new Player(new FileInputStream("res/The Dust Brothers � Marla & Tyler Flashback.mp3"));
		p.play();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JavaLayerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	}

}
