package GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import Background.Background;
import Objects.SpaceObject;

public class MenuState extends GameState{
	//credit: https://www.youtube.com/watch?v=9dzhgsVaiSo - ForeignGuyMike

	private String[] fileBack = {"/Paralax/mars0.png", "/Paralax/mars1.png", "/Paralax/mars2.png", "/Paralax/mars3.png", "/Paralax/mars4.png", "/Paralax/mars5.png"};
	private ArrayList<Background> bg;
	private Background current;
	private int currentChoice = 0;
	private String[] options = {"Start", "Team", "Quit" };
	private Font font;
	Font fRussian;
	SpaceObject title;
	private double[] baseScale = {0, -.1, -.2, -.5, -.7, -1};
	
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			bg = new ArrayList<Background>();
			for (int i =0;i<fileBack.length;i++) {
				current = new Background(fileBack[i], 1);
				current.setVector(baseScale[i], 0);
				bg.add(current);
				
			}
			font = new Font("Arial", Font.PLAIN, 12);
			title = new SpaceObject("/Background/soyuz_title.png");
			title.setPosition(85, 70, 0, 0);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init() {

	}

	@Override
	public void update() {
		for (int i =0;i<fileBack.length;i++) {
			bg.get(i).update();
			//vector update
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		for (int i =0;i<fileBack.length;i++) {
			bg.get(i).draw(g);	
		}

		title.draw(g);
		g.setFont(font);
		for (int i=0; i <options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.BLACK);
			}
			g.drawString(options[i], 145, 150 + i*15);
				
		}
	}

	private void select() {
		if (currentChoice == 0) {
			//gsm.setState(2); //cutscene one
			//gsm.setState(6);
			gsm.setState(7);
		}
		if (currentChoice == 1) {
			gsm.setState(3); //teamstate
		}
		if (currentChoice == 2) {
			System.exit(0);	//quit
		}

	}
	@Override
	public void keyPressed(int k) {

		if (k == KeyEvent.VK_ENTER) {
			select();
		}
		if (k == KeyEvent.VK_UP) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if (k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}

	@Override
	public void keyReleased(int k) {

		
	}

}
