package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import Background.Background;
import Objects.SpaceObject;

public class TeamState extends GameState{
	Background stars;

	Font nameFont;
	private String[] names = {"Jack Hay", "Nick Chkonia", "Alex Black"};
	private SpaceObject credits;
	public TeamState(GameStateManager gsm) {
		this.gsm = gsm;
		stars = new Background("/Background/spaceShipMenu.png", 1);
		//stars.setVector(-1, 0);
		credits = new SpaceObject("/Background/credits.png");
		credits.setPosition(100, 150, 0, 0);
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		stars.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		stars.draw(g);
		credits.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		this.gsm.setState(0);
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
