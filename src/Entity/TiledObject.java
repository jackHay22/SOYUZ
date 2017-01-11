package Entity;

import java.awt.Rectangle;
import java.util.ArrayList;
import TileMap.TileSet;

public abstract class TiledObject {
	
	//tilemap
	protected TileSet tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	//object location and vectors
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	//coordinates for tile at player's feet and below player
	protected int tileX;
	protected int tileY;
	protected int tileY2;
	
	//booleans that define possible movement
	protected boolean right;
	protected boolean left;
	protected boolean up;
	protected boolean down;
	protected boolean falling;
	
	//gravity constant for all objects
	protected double gravity = .75;
	
	//collision detection booleans describing a tile
	protected boolean lBlock;
	protected boolean rBlock;
	protected boolean dBlock;
	protected boolean uBlock;
	
	//specific surfaces
	protected boolean surface;
	protected boolean ceiling;
	protected boolean ladder;
	
	//object attributes
	protected int entityWidth;
	protected int entityHeight;
	protected ArrayList<Animation> animations;
	
	public TiledObject(TileSet tm) {
		
		//tilemap for object
		this.tileMap = tm;
		
		//tile dimension
		this.tileSize = tileMap.getSize();
		
	}
	
	public boolean collision(TiledObject object2) {
		//checks object against current object
		Rectangle player = collisionBox();
		Rectangle other = object2.collisionBox();
		return player.intersects(other);
	}
	protected Rectangle collisionBox() {
		//returns rectangle holding object
		return new Rectangle((int)x, (int)y, entityWidth, entityHeight);
	}
	
	protected void checkVectorBlock(double x, double y) {
		//true if space of next vector spot is blocked.
		this.tileX = (int) (x + entityWidth / 2) /this.tileSize;
		this.tileY = (int) (y + entityHeight / 2) / this.tileSize;
		this.tileY2 = tileY + 1; //TODO
		
		//calculate bounds of tile located at change in y or change in x
		int left = (int)(x + dx - (entityWidth / 2)) / tileSize;
		int right = (int)(x + dx + (entityWidth / 2) - 1) / tileSize;
		int top = (int)(y + dy - (entityHeight / 2)) / tileSize;
		int bottom = (int)(y + dy + gravity + (entityHeight / 2 -1)) / tileSize; 
		
		//checks types
		if (this.tileMap.getType(left, this.tileY) == 1) {lBlock = true;}
		else {lBlock = false;}
		if (this.tileMap.getType(this.tileX, bottom) == 1) {dBlock = true;}
		else {dBlock = false;}
		if (this.tileMap.getType(right, this.tileY) == 1) {rBlock = true;}
		else {rBlock = false;}
		if (this.tileMap.getType(this.tileX, top) == 1) {uBlock = true;}
		else {uBlock = false;}
		
		//set boolean values
		if (tileMap.getType(this.tileX, this.tileY2) == 1) {
			surface = true;
		}
		else {
			surface = false;
		}
		if (tileMap.getType(this.tileX, this.tileY - 1) == 1) {
			ceiling = true;
		}
		else {
			ceiling = false;
		}
		if (tileMap.getType(this.tileX, this.tileY2) == 2) {
			ladder = true;
		}
		else {
			ladder = false;
		} 
		
		
	}
	protected void setRestrictions() {
		this.tileX = (int) x / this.tileSize;
		this.tileY = (int) y / this.tileSize;
		
		//checkRestrictions(this.tileX + 1, this.tileY);
		checkVectorBlock(this.x, this.y);
		//surface check
				
		if (rBlock) {
			if (dx > 0) {
				dx = 0;
			}
			right = false;
		}
		if (ladder) {
			down = true;
			up = true;
		}
		else if (dBlock || surface) {
			if (dy > 0) {
				dy = 0;
			}
			falling = false;
		}
		else { dy += gravity;}
			
		if (lBlock) {
			left = false;
			if (dx < 0) {
				dx = 0;
			}
		}
		if (uBlock || ceiling) {
			up = false;
			if (dy < 0) {
				dy = 0;
			}
		}
	}
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public void setVector(double dx, double dy) {
		
		//set object vector (player overrides this function by removing attributes)
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition() {
		
		//set map variables
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	
}





