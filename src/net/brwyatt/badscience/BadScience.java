package net.brwyatt.badscience;

import java.util.ArrayList;

import net.brwyatt.badscience.levels.levels.TestLevel;
import net.brwyatt.badscience.levels.menus.MainMenu;
import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.Game;
import net.brwyatt.brge.graphics.ScreenObjects;
import net.brwyatt.brge.levels.Level;


public class BadScience extends Game {
	private final String title="Bad Science!";
	private LevelGrid levelGrid;
	private Level currentLevel=null;
	private ArrayList<Level> levels;
	
	public BadScience(ScreenObjects so){
		screenObjects=so;
		levels=new ArrayList<Level>();
		
		levelGrid=new LevelGrid(BRGE.getWidth(),BRGE.getHeight());
		levels.add(new MainMenu(this, screenObjects));
		levels.add(new TestLevel(this, screenObjects,levelGrid));
	}
	
	public void startGame() {
		loadLevel(0);
	}
	public void nextLevel() {
	}
	public void lastLevel() {
	}
	public void loadLevel(int l) {
		if(currentLevel!=null){
			currentLevel.endLevel();
		}
		currentLevel=levels.get(l);
		currentLevel.startLevel();
	}
	public void saveGame() {
	}
	public void loadGame() {
	}
	public void pauseGame() {
	}
	public void unpauseGame() {
	}
	public void showInGameMenu() {
	}
	public void keyPressed(int key) {
		currentLevel.keyPressed(key);
	}
	public void keyReleased(int key) {
		currentLevel.keyReleased(key);
	}
	public void keyTyped(int key) {
		currentLevel.keyTyped(key);
	}
	public String getGameTitle() {
		return title;
	}
}
