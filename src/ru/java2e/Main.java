package ru.java2e;
import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame f = new JFrame("Java Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1100, 600);
		f.setBounds(500, 200, 1100, 600);
		f.add(new Road());
		f.setVisible(true);
	}

}
