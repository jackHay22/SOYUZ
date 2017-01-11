package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Background.Background;
import Background.GameBackground;
import Main.GamePanel;
import Objects.SpaceObject;

public class SceneTwo extends GameState{
	SpaceObject komorov;
	SpaceObject dialogue;
	GameBackground sky;
	Background stars;
	private double xKom = 20;
	private double yKom = GamePanel.HEIGHT - 50;
	private GameStateManager gsm;

	private Color textColor;
	private Font textFont;
	
	public SceneTwo(GameStateManager gsm) {
		this.gsm = gsm;
		komorov = new SpaceObject("/Faces/komorov.png");
		komorov.setPosition(xKom, yKom, 0, 0);
		
		stars = new Background("/Scenes/staticStars.png", 1);
		stars.setVector(-0.1, 0);
		dialogue = new SpaceObject("/Scenes/dialogue.png");
		dialogue.setPosition(xKom, yKom, 0, 0);
		textColor = new Color(0, 0, 0);
		textFont = new Font("Century Gothic", Font.PLAIN, 10);
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub			
		stars.update();
		dialogue.update();
		komorov.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		stars.draw(g);
		dialogue.draw(g);
		komorov.draw(g);
		g.setColor(textColor);
		g.setFont(textFont);
		g.drawString("FAILURE, comrade", (int) xKom + 50, (int) yKom + 20);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_ENTER) {
			this.gsm.setState(0);
		}
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
