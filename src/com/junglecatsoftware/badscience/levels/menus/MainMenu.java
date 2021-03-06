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

package com.junglecatsoftware.badscience.levels.menus;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.junglecatsoftware.badscience.BadScience;
import com.junglecatsoftware.badscience.drawables.MainMenuBackground;
import com.junglecatsoftware.brge.BRGE;
import com.junglecatsoftware.brge.Game;
import com.junglecatsoftware.brge.graphics.ScreenObjects;
import com.junglecatsoftware.brge.graphics.drawables.MenuItem;
import com.junglecatsoftware.brge.levels.Level;


public class MainMenu extends Level{
	
	private ArrayList<MenuItem> items;
	private int selectedItem=0;
	private boolean menuSelected=false; //Ensure that menu items can't be triggered if the spacebar was pressed prior to the menu loading
	
	public MainMenu(Game g, ScreenObjects so){
		super(g, so);
	}

	public void startLevel() {
		screenObjects.clear();
		screenObjects.addToTop(new MainMenuBackground());

		items=new ArrayList<MenuItem>();
		items.add(new MenuItem(0, true, "TEST LEVEL"));
		items.add(new MenuItem(1, "Toggle DrawFPS"));
		items.add(new MenuItem(2, "Draw Shadows ("+BRGE.getDrawShadows()+")"));
		items.add(new MenuItem(4, "Exit"));
		for(MenuItem item : items){
			screenObjects.addToTop(item);
		}
		
		selectedItem=0;
	}
	public void endLevel() {
		screenObjects.clear();
	}
	
	private void menuUp(){
		items.get(selectedItem).setSelected(false);
		selectedItem--;
		if(selectedItem<0){
			selectedItem=items.size()-1;
		}
		items.get(selectedItem).setSelected(true);
	}
	private void menuDown(){
		items.get(selectedItem).setSelected(false);
		selectedItem++;
		if(selectedItem>=items.size()){
			selectedItem=0;
		}
		items.get(selectedItem).setSelected(true);
	}
	private void menuActivated(){
		switch(selectedItem){
			case 0:
				game.loadLevel(1);
				break;
			case 1:
				BRGE.toggleFPS();
				break;
			case 2:
				BRGE.toggleShadows();
				items.get(selectedItem).setText("Draw Shadows ("+BRGE.getDrawShadows()+")");
				break;
			case 3:
				BRGE.exit();
				break;
		}
	}
	
	public void keyPressed(int key) {
		switch(key){
			case KeyEvent.VK_UP:
				menuUp();
				break;
			case KeyEvent.VK_DOWN:
				menuDown();
				break;
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:
				menuSelected=true;
				break;
		}
		
	}
	public void keyReleased(int key) {
		switch(key){
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_ENTER:
				if(menuSelected){
					menuSelected=false;
					menuActivated();
				}
				break;
		}
	}
	public void keyTyped(int key) {
	}
}
