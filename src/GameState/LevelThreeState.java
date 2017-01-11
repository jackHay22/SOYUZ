package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import Background.Background;
import Entity.Player;
import Main.GamePanel;
import TileMap.TileSet;

public class LevelThreeState extends GameState {
	private TileSet tilemap;
	private TileSet tilemapB;
	private TileSet tilemapF;
	private Background back;
	Player playerOne;
	
	public LevelThreeState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}
	@Override
	public void init() {

		//terrain
		tilemap = new TileSet("/Tilesets/shipDepth.png", 16, 1);
		tilemap.loadMap("/Maps/shipDepthT.txt");
		tilemap.setPosition(0, 0);
		
		//back layer
		tilemapB = new TileSet("/Tilesets/shipDepth.png", 16, 1);
		tilemapB.loadMap("/Maps/shipDepthback.txt");
		tilemapB.setPosition(0, 0);
		
		//front layer
		tilemapF = new TileSet("/Tilesets/shipDepth.png", 16, 1);
		tilemapF.loadMap("/Maps/shipDepthfront.txt");
		tilemapF.setPosition(0, 0);
		
		//player
		playerOne = new Player(tilemap);
		playerOne.setPosition(64, 64);
		
		//moving background
		back = new Background("/Scenes/staticStars.png", 0);
		back.setVector(-.25, 0);

	}

	@Override
	public void update() {

		back.update();
		playerOne.update();
		tilemap.setPosition(GamePanel.WIDTH / 2 - playerOne.getX(), GamePanel.HEIGHT / 2 - playerOne.getY());
		tilemapB.setPosition(GamePanel.WIDTH / 2 - playerOne.getX(), GamePanel.HEIGHT / 2 - playerOne.getY());
		tilemapF.setPosition(GamePanel.WIDTH / 2 - playerOne.getX(), GamePanel.HEIGHT / 2 - playerOne.getY());
		
		//back.setPosition(GamePanel.WIDTH / 2 - playerOne.getX(), GamePanel.HEIGHT / 2 - playerOne.getY());
	}

	@Override
	public void draw(Graphics2D g) {

		//draw stuff
		back.draw(g);
		tilemapB.draw(g);
		tilemap.draw(g);
		playerOne.draw(g);
		tilemapF.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {

		//set actions, tileobject takes actions, compares with restrictions and performs movement
		if (k == KeyEvent.VK_SPACE) {
			playerOne.jumping = true;
		}
		if (k == KeyEvent.VK_RIGHT) {
			playerOne.Rwalking = true;
		}
		if (k == KeyEvent.VK_LEFT) {
			playerOne.Lwalking = true;
		}
		if (k == KeyEvent.VK_DOWN) {
			playerOne.climbingD = true;
		}
		if (k == KeyEvent.VK_UP) {
			playerOne.climbingU = true;
		}
		
		//set player directions
		playerOne.setVector();
	}

	@Override
	public void keyReleased(int k) {

		//if released, set actions to false
		if (k == KeyEvent.VK_SPACE) {
			playerOne.jumping = false;
		}
		if (k == KeyEvent.VK_RIGHT) {
			playerOne.Rwalking = false;
		}
		if (k == KeyEvent.VK_LEFT) {
			playerOne.Lwalking = false;
		}
		if (k == KeyEvent.VK_DOWN) {
			playerOne.climbingD = false;
		}
		if (k == KeyEvent.VK_UP) {
			playerOne.climbingU = false;
		}
		
		//set player directions
		playerOne.setVector();
		
	}

}
