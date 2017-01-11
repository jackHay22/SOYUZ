package Background;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import Main.GamePanel;

public class Background {
	private BufferedImage image;
	private double x;
	private double y;
	private double dx;
	private double dy;
	private double x2 = 0;
	private double y2 = 0;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		//credit: https://www.youtube.com/watch?v=9dzhgsVaiSo - ForeignGuyMike
		
		//import background resource
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
			moveScale = ms;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}
	public void update() {
		x += dx;
		y += dy;
		x2 += dx;
		y2 += dy;
	}
	public void draw(Graphics2D g) {
		g.drawImage(image, (int)x, (int)y, null);
		
		if(x < 0) {
			x2 = (int)x + GamePanel.WIDTH;
			g.drawImage(image, (int)x2, (int)y, null);
		}
		if(x > 0) {
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
		}
		if (x2 < 0) {
			x = (int)x2 + GamePanel.WIDTH;
			g.drawImage(image, (int)x, (int)y, null);
		}
		
		if(y > 0) {
			y2 = (int) y - GamePanel.HEIGHT;
			g.drawImage(image, (int)x, (int)y2, null);
		}
		if(y < 0) {
			g.drawImage(image, (int)x, (int)y + GamePanel.HEIGHT, null);
		}
		if(y2 > 0) {
			y = (int) y2 - GamePanel.HEIGHT;
			g.drawImage(image, (int)x, (int)y, null);
		}

	}
}
