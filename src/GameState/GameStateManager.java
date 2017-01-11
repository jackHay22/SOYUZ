package GameState;
import java.util.ArrayList;

public class GameStateManager {
	//credit: https://www.youtube.com/watch?v=9dzhgsVaiSo - ForeignGuyMike
	private ArrayList<GameState> gameStates;
	private int currentState;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int CUTSCENE1 = 2;
	public static final int TEAMSTATE = 3;
	public static final int CUTSCENE2 = 4;
	public static final int CUTSCENE3 = 5;
	public static final int LEVEL2STATE = 6;
	public static final int LEVEL3STATE = 7;
	
	
	public GameStateManager() {
		gameStates = new ArrayList<GameState>();
		currentState = MENUSTATE;
		gameStates.add(MENUSTATE, new MenuState(this));
		gameStates.add(LEVEL1STATE, new LevelOneState(this));
		gameStates.add(CUTSCENE1, new SceneOne(this));
		gameStates.add(TEAMSTATE, new TeamState(this));
		gameStates.add(CUTSCENE2, new SceneTwo(this));
		gameStates.add(CUTSCENE3, new SceneThree(this));
		gameStates.add(LEVEL2STATE, new LevelTwoState(this));
		gameStates.add(LEVEL3STATE, new LevelThreeState(this));
		
	}
	public void setState(int state) {
		if (state == 0) {
			gameStates.set(LEVEL1STATE,  new LevelOneState(this));
			gameStates.set(LEVEL2STATE,  new LevelTwoState(this));
			gameStates.set(LEVEL3STATE,  new LevelThreeState(this));
		}

		currentState = state;
		gameStates.get(currentState).init(); 
		
	}
	public void update() {
		gameStates.get(currentState).update();
	}
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

 }
