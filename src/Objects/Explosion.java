package Objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Explosion extends Objects {
	private BufferedImage image1;
	private BufferedImage image2;
	private BufferedImage image3;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private int total;
	private int currentImage; 
	BufferedImage[] allImages = new BufferedImage[3];
	
	public Explosion(String s1, String s2, String s3){
		try {
			image1 = ImageIO.read(getClass().getResourceAsStream(s1));
			image2 = ImageIO.read(getClass().getResourceAsStream(s2));
			image3 = ImageIO.read(getClass().getResourceAsStream(s3));
			allImages[0] = image1;
			allImages[0] = image2;
			allImages[0] = image3;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.currentImage = 0;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.x = (this.x + this.dx) % (GamePanel.WIDTH + 5);
		this.y = (this.y + this.dy) % (GamePanel.HEIGHT + 5);
		if (this.x < 0) {
			this.x = GamePanel.WIDTH;
		}
		if (this.y < 0) {
			this.y = GamePanel.HEIGHT;
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if (total < 3) {
			g.drawImage(allImages[currentImage], (int)x, (int)y, null);
			currentImage ++;
			if (currentImage == 3) {
				currentImage = 0;
			}
			total++;
		}
	}

	@Override
	public void setPosition(double x, double y, double dx, double dy) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	@Override
	public void setGravity(double grav) {
		// TODO Auto-generated method stub
		
	}

}
