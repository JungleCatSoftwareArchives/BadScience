package net.brwyatt.badscience;

import java.util.ArrayList;

import net.brwyatt.badscience.levels.MainMenu;
import net.brwyatt.brge.Game;
import net.brwyatt.brge.graphics.ScreenObjects;
import net.brwyatt.brge.levels.Level;


public class BadScience extends Game {
	private final String title="Bad Science!";
	private Level currentLevel=null;
	private ArrayList<Level> levels;
	
	public BadScience(ScreenObjects so){
		screenObjects=so;
		levels=new ArrayList<Level>();

		levels.add(new MainMenu(this, screenObjects));
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
