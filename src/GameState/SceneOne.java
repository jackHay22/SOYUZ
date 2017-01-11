package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Background.Background;
import Main.GamePanel;
import Objects.SpaceObject;

public class SceneOne extends GameState{
	SpaceObject yuri;
	SpaceObject dialogue;
	Background sky;
	private String[] fileBack = {"/Paralax/earth1.png", "/Paralax/earth2.png", "/Paralax/earth3.png", "/Paralax/earth4.png"};
	private double[] baseScale = {0, -.3, -.7, -1.2};
	private ArrayList<Background> bg;
	
	Background launch;
	private double xYuri = 20;
	private double yYuri = GamePanel.HEIGHT - 50;
	private GameStateManager gsm;

	private Color textColor;
	private Font textFont;
	
	public SceneOne(GameStateManager gsm) {
		this.gsm = gsm;
		yuri = new SpaceObject("/Faces/yuri.png");
		yuri.setPosition(xYuri, yYuri, 0, 0);
		sky = new Background("/Background/sky3.png",0);
		launch = new Background("/Scenes/launch.png", 0);

		bg = new ArrayList<Background>();
		Background earth;
		for (int i =0;i<fileBack.length;i++) {
			earth = new Background(fileBack[i],1);
			earth.setVector(baseScale[i], 0);
			bg.add(earth);
		}
		
		dialogue = new SpaceObject("/Scenes/dialogue.png");
		dialogue.setPosition(xYuri, yYuri, 0, 0);
		textColor = new Color(0, 0, 0);
		textFont = new Font("Century Gothic", Font.PLAIN, 10);
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		launch.update();
		for (int i = 0; i< fileBack.length; i++) {
			bg.get(i).update();
		}
		dialogue.update();
		yuri.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		sky.draw(g);
		launch.draw(g);
		for (int i = 0; i< fileBack.length; i++) {
			bg.get(i).draw(g);
		}
		dialogue.draw(g);
		yuri.draw(g);
		g.setColor(textColor);
		g.setFont(textFont);
		g.drawString("TEST TEXT (Yuri Gagarin)", (int) xYuri + 50, (int) yYuri + 20);
		
		
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
	}
	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_ENTER) {
			this.gsm.setState(1);
		}
			
	}
	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
