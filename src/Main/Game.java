package Main;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		//credit: https://www.youtube.com/watch?v=9dzhgsVaiSo - ForeignGuyMike
		JFrame window = new JFrame("Soyuz");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}
