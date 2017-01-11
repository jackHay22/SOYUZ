package Background;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Main.GamePanel;

public class Star {
	private int x;
	private int y;
	private float z;
	private float speed;
	private Random random;
	public Star() {
		random = new Random();
		x = random.nextInt(x) - x;
		y = random.nextInt(y) - y;
		z = random.nextInt(x);
		speed = random.nextFloat();
		
	}
	public void update() {
		z -= speed;
		if (z < 1) {
			x = random.nextInt(x) - x;
			y = random.nextInt(y) - y;
			z = random.nextInt(x);
		}
	}
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
	    g.fillRect(x,y,1,1);
	}
}
