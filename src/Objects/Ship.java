package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Ship extends Objects{
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double rotation;
	public int width = 13;
	public int height = 40;
	private double grav;
	
	


	public Ship(String s) {
		this.rotation = 0;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update() {

		this.x = (this.x + dx) % GamePanel.WIDTH;
		this.y = (this.y + dy) % GamePanel.HEIGHT;
		
		if (this.x < 0) {
			this.x = GamePanel.WIDTH;
		}
		if (this.y < 0) {
			this.y = GamePanel.HEIGHT;
		}
		
		this.dx *= .95;
		this.dy *= .95;
		this.y += this.grav;
		
	}
	public void move(double dx, double dy, double rot) {
		this.dx += dx;
		this.dy += dy;
		this.rotation += rot;
		
	}

	@Override
	public void draw(Graphics2D g) {
		

		
		g.drawImage(image, (int) this.x, (int) this.y, null);
		//this.rotation *= .90;
	}

	@Override
	public void setPosition(double x, double y, double dx, double dy) {
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double centerX() {
		return this.x + (this.width / 2);
	}
	public double centerY() {
		return this.y + (this.height / 2);
	}
	public void setGravity(double gravityVector) {
		this.grav = gravityVector;
	}

	

}
