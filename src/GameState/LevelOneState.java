package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import Background.Background;
import Main.GamePanel;
import Objects.Bullet;
import Objects.Explosion;
import Objects.Ship;
import Objects.SpaceObject;

public class LevelOneState extends GameState {
	//GameBackground space;
	Background space;
	SpaceObject newRock;
	Explosion destroy;
	private double xMax = GamePanel.WIDTH;
	private double yMax = GamePanel.HEIGHT;
	
	private int totalRocks = 5;
	private int wavesRemaining = 2;
	private int totalLives = 5;
	private ArrayList<SpaceObject> rocks;
	
	private Random random;
	private Ship playerOne;
	
	private ArrayList<Bullet> shotArray;
	SpaceObject heart;
	private ArrayList<SpaceObject> lives;

	
	
	public LevelOneState(GameStateManager gsm) {
		this.gsm = gsm;
		//space = new GameBackground(Color.black);
		//initialize so draw works
		destroy = new Explosion("/Objects/rockBreak1.png", "/Objects/rockBreak2.png", "/Objects/rockBreak3.png");
		destroy.setPosition(-30, -30, 0, 2);
		space = new Background("/Scenes/staticStars.png",1);
		space.setVector(0, 1);
		
		rocks = new ArrayList<SpaceObject>();
		shotArray = new ArrayList<Bullet>();
		lives = new ArrayList<SpaceObject>();
		
		random = new Random();
		//add rocks to array
		for (int i = 0; i < totalRocks;i++) {
			newRock = new SpaceObject("/Objects/rock.png");
			newRock.setPosition(random.nextInt((int) xMax), random.nextInt((int) yMax), (random.nextDouble() - .5) * 2, (random.nextDouble() - .5) * 2);
			rocks.add(newRock);
		}
		//add hearts to array
		for (int i = 0; i <totalLives; i++) {
			heart = new SpaceObject("/Objects/life.png");
			heart.setPosition(15 + (i*20), 15 ,0 , 0);
			lives.add(heart);
		}
		//build player
		playerOne = new Ship("/Objects/ship2.png");
		playerOne.setPosition(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, 0, 0);

		
	}
	@Override
	public void init() {

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//objects update
		space.update();
		if (rocks.size() < 3 && wavesRemaining > 0) {
			for (int i = 0; i < 7;i++) {
				newRock = new SpaceObject("/Objects/rock.png");
				newRock.setPosition(random.nextInt(GamePanel.WIDTH), random.nextInt(40) * -1, (random.nextDouble() - .5) * 3, (random.nextDouble() - .5) * 3);
				rocks.add(newRock);
			}
			wavesRemaining --;
		}
		for (int i=0;i<rocks.size();i++) {
			rocks.get(i).update();
		}
		//remove shots if out of bounds
		for (int i =0;i < shotArray.size(); i++) {
			if (shotArray.get(i).getX() > GamePanel.WIDTH || shotArray.get(i).getX() < 0) {
				shotArray.remove(i);
			}
			else if (shotArray.get(i).getY() > GamePanel.HEIGHT || shotArray.get(i).getY() < 0) {
				shotArray.remove(i);
			}
			else {
				//else update shot location
				shotArray.get(i).update();
			}
		}
		//check collision of bullets and rocks
		double distanceX;
		double distanceY;
		double shotX;
		double shotY;
		for( int counter = shotArray.size() - 1; counter >= 0;counter--) { 
			//get center of current bullet
			shotX = shotArray.get(counter).getX() + 1;
			shotY = shotArray.get(counter).getY() + 1;
			
			//check against location of all rocks
			for (int i =  rocks.size() - 1; i >= 0 ; i--){
				distanceX = shotX - (rocks.get(i).getX() + 10);
				distanceY = shotY - (rocks.get(i).getY() + 10);
				if( Math.abs(distanceX) < 7 && Math.abs(distanceY) < 7) {
					//if within radius, remove
					
//					destroy = new Explosion("/Objects/rockBreak1.png", "/Objects/rockBreak2.png", "/Objects/rockBreak3.png");
//					destroy.setPosition(rocks.get(i).getX(), rocks.get(i).getX(), 0, 0);
//					destroy.update();
					rocks.remove(i);
					shotArray.remove(counter);
				}
			}
		}
		playerOne.update();
		//player collision
		if (lives.size() <= 0) { 
			this.gsm.setState(4);
		}
		if (rocks.size() <= 0) {
			this.gsm.setState(5);
		}
		else {	
			for (int i =  rocks.size() - 1; i >= 0 ; i--){
				//check player position against all rock objects
				
				if (((Math.abs(playerOne.centerX() - (rocks.get(i).getX() + 10)) < 17)) && (Math.abs(playerOne.centerY() - (rocks.get(i).getY() + 10)) < 17)) {
					lives.remove(lives.size()-1);
					rocks.remove(i);
				}
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		space.draw(g);
		//destroy.draw(g);
		//draw rocks
		for (int i=0;i<rocks.size();i++) {
			rocks.get(i).draw(g);
		}
		//draw shots
		for (int i =0;i < shotArray.size(); i++) {
			shotArray.get(i).draw(g);
		}
		//draw hearts
		for (int i=0;i<lives.size();i++) {
			lives.get(i).draw(g);
		}
		playerOne.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_UP) {
			playerOne.move(0, -3, 0);
		}
		if (k == KeyEvent.VK_DOWN) {
			playerOne.move(0, 3, 0);
		}
		if (k == KeyEvent.VK_SPACE) {
			Bullet shot = new Bullet("/Objects/shot.png");
			shot.setPosition(playerOne.centerX(), playerOne.centerY(), 0, -5);
			shotArray.add(shot);
		}
		if (k == KeyEvent.VK_RIGHT) {
			//playerOne.move(0, 0, 5);
			playerOne.move(3, 0, 0);
		}
		if (k == KeyEvent.VK_LEFT) {
			//playerOne.move(0, 0, -5);
			playerOne.move(-3, 0, 0);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
