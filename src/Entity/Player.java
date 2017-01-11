package Entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import TileMap.TileSet;

public class Player extends TiledObject{
	private BufferedImage temp;
	
	//player specific actions
	public boolean Lwalking;
	public boolean Rwalking;
	public boolean jumping;
	public boolean climbingD;
	public boolean climbingU;
	
	//player specific movement values
	private int jump = -4;
	private double walk = 1.2;
	private double climb = .75;
	
	//animations
	private Animation walkingRAnimation;
	private Animation walkingLAnimation;
	private Animation idleAnimation;
	private Animation jumpingAnimation;
	private Animation climbingAnimation;
	private Animation deathAnimation;
	private Animation shot;
	
	private Animation currentAnimation;
	
	public Player(TileSet tm) {
		//call TiledObject super-class constructor
		super(tm);
		
		//try to load stand-in image	
		try {
			temp = ImageIO.read(getClass().getResourceAsStream("/Characters/cosmonaut.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		shot = new Animation("/Animations/shot.png", 8);
		shot.setDelay(40);
	}
	public void draw(Graphics2D g) {
		//set map position relative to player	
		setMapPosition();
		
		//draw player at player location (with width) plus the map x, y
		g.drawImage(temp,(int)(x + xmap - temp.getWidth() / 2), (int)(y + ymap - temp.getHeight() / 2), null);
		g.drawImage(shot.getCurrentImage(), (int)(x + xmap - temp.getWidth() / 2) + 15, (int)(y + ymap - temp.getHeight() / 2) + 10, null);
	}
	public void update() {
		//tilemap collision checks
		setRestrictions();
		
		//set object position with change in x and y after checking collisions in both directions
		//if a collision happens at next position, change is set to zero
		setPosition(this.x += this.dx, this.y += this.dy);
		shot.update();

	}

	public void setVector() {
		if (tileMap.getType(this.tileX, this.tileY) == 2) {
			//can't climb and jump
			jumping = false;
		}
		
		
		if (Rwalking) {
			//if walking right, set walking to positive walk value
			this.dx = walk;
		}
		else if (Lwalking) {
			//if walking left, set walking to positive walk value
			this.dx = -walk;
		}
		else {
			this.dx = 0;
		}
		
		if (jumping) {
			this.dy = jump;
			jumping = false;
		}
		else if (climbingU) {
			this.dy = -climb;
		}
		else if (climbingD) {
			this.dy = climb;
		}
		else {
			this.dy = 0;
		}
			
			
	}
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

}
