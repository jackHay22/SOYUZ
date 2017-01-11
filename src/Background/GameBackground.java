package Background;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;

public class GameBackground {
	private Color color;
	
	public GameBackground(Color color) {
		this.color = color;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(this.color);
	    g.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
	    
	}
}
