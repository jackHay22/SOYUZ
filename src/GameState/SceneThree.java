package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Background.Background;
import Main.GamePanel;
import Objects.SpaceObject;

public class SceneThree extends GameState{
	SpaceObject rasputin;
	SpaceObject dialogue;

	private String[] fileBack = {"/Paralax/merc0.png", "/Paralax/merc1.png", "/Paralax/merc2.png", "/Paralax/merc3.png"};
	private ArrayList<Background> bg;
	
	private double xYuri = 20;
	private double yYuri = GamePanel.HEIGHT - 50;
	private GameStateManager gsm;

	private Color textColor;
	private Font textFont;
	
	public SceneThree(GameStateManager gsm) {
		this.gsm = gsm;
		rasputin = new SpaceObject("/Faces/rasputin.png");
		rasputin.setPosition(xYuri, yYuri, 0, 0);
		
		bg = new ArrayList<Background>();
		Background merc;
		for (int i =0;i<fileBack.length;i++) {
			merc = new Background(fileBack[i],1);
			merc.setVector(0, 0);
			bg.add(merc);
		}
		//saturn = new SpaceObject("/Scenes/saturn2.png");
		//saturn.setPosition(25, 25, .01, 0);

		dialogue = new SpaceObject("/Scenes/dialogue.png");
		dialogue.setPosition(xYuri, yYuri, 0, 0);
		textColor = new Color(0, 0, 0);
		textFont = new Font("Century Gothic", Font.PLAIN, 10);
	}
	@Override
	public void init() {
		
	}

	@Override
	public void update() {		

	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		for (int i = 0; i< fileBack.length; i++) {
			bg.get(i).draw(g);
		}
		//saturn.draw(g);

		dialogue.draw(g);
		rasputin.draw(g);
		g.setColor(textColor);
		g.setFont(textFont);
		g.drawString("Success, now on to the surface of", (int) xYuri + 50, (int) yYuri + 20);
		g.drawString("Mercury.", (int) xYuri + 50, (int) yYuri + 35);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_ENTER) {
			this.gsm.setState(6);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}

