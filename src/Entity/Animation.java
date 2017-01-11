package Entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Animation {
	
	//sprite attributes
	private BufferedImage image;
	private int width;
	
	//sprite sheet attributes
	private int framesAcross;
	
	//animation
	private ArrayList<Frame> animationFrames;
	private int currentFrame;
	
	//timing
	private long start;
	private long delay = 400; //default
	
	
	public Animation(String s, int width) {
		//animation frame width
		this.width = width;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(s));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//calculate frames on sheet
		framesAcross = image.getWidth() / this.width;
		
		//timing
		start = System.nanoTime();
		animationFrames = new ArrayList<Frame>();
		
		//load frames from sheet
		for (int i = 0; i < framesAcross * width; i+= width) {

			animationFrames.add(new Frame(image.getSubimage(i, 0, this.width, image.getHeight())));
		}
	}
	
	public BufferedImage getCurrentImage() {
		return animationFrames.get(currentFrame).getImage();
	}
	
	public void setDelay(long delay) {
		//recommended: ~400
		this.delay = delay;
	}
	
	public void update() {
		long elapsed = (System.nanoTime() - start) / 1000000;
		
		if(elapsed > delay) {
			//prevent overrun
			currentFrame++;
			if (currentFrame == framesAcross) {
				currentFrame = 0;
			}
			start = System.nanoTime();
		}
		
	}
}
