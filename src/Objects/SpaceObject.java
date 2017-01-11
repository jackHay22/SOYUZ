package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class SpaceObject extends Objects{
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double rotation;
	private double grav;

	public SpaceObject(String s) {

		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		//g.rotate(Math.toRadians(this.rotation), this.x , this.y );
	    
	}
	public void update() {
		//rotation += rotation;
		this.x = (this.x + this.dx) % (GamePanel.WIDTH + 5);
		this.y = (this.y + this.dy) % (GamePanel.HEIGHT + 5);
		if (this.x < 0) {
			this.x = GamePanel.WIDTH;
		}
		if (this.y < 0) {
			this.y = GamePanel.HEIGHT;
		}
		this.y += grav;
		
	}
	public void setPosition(double x, double y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		//this.rotation = rotation; //change in rotation
	}
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public void setGravity(double grav) {
		this.grav = grav;
	}
}
