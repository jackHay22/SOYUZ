package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bullet extends Objects{
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double grav;
	
	public Bullet(String s) {
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update() {

		this.x += this.dx;
		this.y += this.dy;
		this.y += grav;
	}

	@Override
	public void draw(Graphics2D g) {

		g.drawImage(image, (int)x, (int)y, null);
	}

	@Override
	public void setPosition(double x, double y, double dx, double dy) {

		this.dx = dx;
		this.dy = dy;
		this.x = x;
		this.y = y;
		
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
