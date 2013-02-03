/* 
 * Copyright 2012 Bryan Wyatt
 * 
 * This file is part of BadScience!.
 *  
 * BadScience! is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * BadScience! is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with BadScience!.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.junglecatsoftware.badscience;

import java.util.ArrayList;

import com.junglecatsoftware.badscience.levels.levels.TestLevel;
import com.junglecatsoftware.badscience.levels.menus.MainMenu;
import com.junglecatsoftware.brge.BRGE;
import com.junglecatsoftware.brge.Game;
import com.junglecatsoftware.brge.graphics.ScreenObjects;
import com.junglecatsoftware.brge.levelgrid.LevelGrid;
import com.junglecatsoftware.brge.levels.Level;



public class BadScience extends Game {
	private final String title="Bad Science!";
	private Level currentLevel=null;
	private ArrayList<Level> levels;
	
	public BadScience(ScreenObjects so){
		screenObjects=so;
		levels=new ArrayList<Level>();
		
		levels.add(new MainMenu(this, screenObjects));
		levels.add(new TestLevel(this, screenObjects));
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
