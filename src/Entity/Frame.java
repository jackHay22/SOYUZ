package Entity;

import java.awt.image.BufferedImage;

public class Frame {

	private BufferedImage image;
	
	public Frame(BufferedImage image) {
		this.image = image;
	}
	public BufferedImage getImage() {
		return this.image;
	}
}
