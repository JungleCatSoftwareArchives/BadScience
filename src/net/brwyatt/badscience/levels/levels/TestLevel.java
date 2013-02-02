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

package net.brwyatt.badscience.levels.levels;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import net.brwyatt.badscience.drawables.GridOverlay;
import net.brwyatt.badscience.drawables.FloorTile;
import net.brwyatt.badscience.drawables.WallTile;
import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.Game;
import net.brwyatt.badscience.drawables.PauseMenuOverlayBackground;
import net.brwyatt.brge.graphics.ScreenObjects;
import net.brwyatt.brge.graphics.drawables.BlackBackground;
import net.brwyatt.brge.graphics.drawables.LevelGridDrawable;
import net.brwyatt.brge.graphics.drawables.MenuItem;
import net.brwyatt.brge.levelgrid.LevelGrid;
import net.brwyatt.brge.levelgrid.LevelGridSquare;
import net.brwyatt.brge.levels.Level;


public class TestLevel extends Level{
	
	private boolean run=true;
	private boolean pause=false;
	private Thread t;
	private GridOverlay overlay;
	private boolean showOverlay;
	private boolean startShiftLeft;
	private boolean startShiftRight;
	private boolean startShiftUp;
	private boolean startShiftDown;
	private boolean shiftingLeft;
	private boolean shiftingRight;
	private boolean shiftingUp;
	private boolean shiftingDown;
	
	private int topFloorTile;
	private int topWallTile;
	
	private LevelGrid levelGrid;
	
	private boolean exitselected=false;
	
	public TestLevel(Game g, ScreenObjects so){
		super(g, so);
		levelGrid=new LevelGrid(BRGE.getWidth(),BRGE.getHeight());
	}
	public TestLevel(Game g, ScreenObjects so, LevelGrid lg){
		super(g, so);
		levelGrid=lg;
	}

	public void startLevel() {
		screenObjects.clear();
		levelGrid.resetGrid();

		screenObjects.addToBottom(new BlackBackground());
		
		//initialize index for objects
		topFloorTile=topWallTile=screenObjects.count()-1;
		
		for(int y=0;y<levelGrid.getGridHeight();y+=1){
			for(int x=0;x<levelGrid.getGridWidth();x+=1){
				LevelGridSquare square=levelGrid.getGridSquare(x, y);
				loadTile(square);
			}
		}
		
		overlay=new GridOverlay(levelGrid);
		showOverlay=true;
		screenObjects.addToTop(overlay);

		t=new Thread(){ public void run(){ runGame(); }};
		t.start();
	}
	public void endLevel() {
		run=false;
		pause=false;
		while(t.isAlive()){
			wait(1);
		}
		screenObjects.clear();
		levelGrid.resetGrid();
		
		shiftingLeft=false;
		shiftingRight=false;
		shiftingUp=false;
		shiftingDown=false;
	}
	public void keyPressed(int key) {
		switch(key){
			case KeyEvent.VK_LEFT:
				startShiftRight=true;
				break;
			case KeyEvent.VK_RIGHT:
				startShiftLeft=true;
				break;
			case KeyEvent.VK_UP:
				startShiftDown=true;
				break;
			case KeyEvent.VK_DOWN:
				startShiftUp=true;
				break;
			case KeyEvent.VK_SPACE:
				if(pause){
					exitselected=true;
				}
				break;
			case KeyEvent.VK_ENTER:
				if(pause){
					exitselected=true;
				}
				break;
		}
	}
	public void keyReleased(int key) {
		switch(key){
			case KeyEvent.VK_LEFT:
				startShiftRight=false;
				break;
			case KeyEvent.VK_RIGHT:
				startShiftLeft=false;
				break;
			case KeyEvent.VK_UP:
				startShiftDown=false;
				break;
			case KeyEvent.VK_DOWN:
				startShiftUp=false;
				break;
			case KeyEvent.VK_SPACE:
				if(pause && exitselected){
					game.loadLevel(0);
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(pause){
					exitselected=false;
					pause=false;
				}else{
					pause=true;
				}
				break;
			case KeyEvent.VK_ENTER:
				if(pause && exitselected){
					game.loadLevel(0);
				}
				break;
			case KeyEvent.VK_G:
				if(!pause){
					if(showOverlay){
						showOverlay=false;
						screenObjects.remove(overlay);
					}else{
						showOverlay=true;
						screenObjects.addToTop(overlay);
					}
				}
				break;
		}
	}
	public void keyTyped(int key) {
		switch(key){
		}
	}
	
	private void runGame(){
		run=true;
		int counter=1; //tick-tock counter
		while(run){
			
			if(pause){//if game has been paused
				//create and display menu
				PauseMenuOverlayBackground bg=new PauseMenuOverlayBackground();
				MenuItem item1=new MenuItem(4, true, "Exit to Main Menu");
				screenObjects.addToTop(bg);
				screenObjects.addToTop(item1);
				
				while(pause){
					wait(10);
				}
				
				//remove menu from screen
				screenObjects.remove(bg);
				screenObjects.remove(item1);
			}
			
			if(counter==1){
				if(startShiftLeft&&(!startShiftRight)){
					shiftingLeft=true;
				}else if(startShiftRight&&(!startShiftLeft)){
					shiftingRight=true;
				}
				if(startShiftUp&&(!startShiftDown)){
					shiftingUp=true;
				}else if(startShiftDown&&(!startShiftUp)){
					shiftingDown=true;
				}
			}
			
			//Only update 50 times/second, and only when actually moving
			int stepRegulator=20;
			int stepCount=(1000/stepRegulator);
			if((counter%stepRegulator==0)&&(shiftingLeft||shiftingRight||shiftingUp||shiftingDown)){
				if(shiftingRight||(shiftingDown&&(!shiftingLeft))){//use up then left navigation
					for(int i=levelGrid.getGridWidth()-1;i>=0;i--){
						for(int j=levelGrid.getGridHeight()-1;j>=0;j--){
							//System.out.println("U-L: ("+i+","+j+")");
							shift(counter,stepCount,i,j);
						}
					}
				}else{//Use down then right navigation
					for(int i=0;i<levelGrid.getGridWidth();i++){
						for(int j=0;j<levelGrid.getGridHeight();j++){
							//System.out.println("D-R: ("+i+","+j+")");
							shift(counter,stepCount,i,j);
						}
					}
				}
			}
			
			if(counter==1000){//reset shifting
				
				if(shiftingLeft||shiftingRight){
					LevelGridSquare square;
					int x=0;
					if(shiftingLeft){
						square = levelGrid.getGridSquare(levelGrid.getGridWidth()-1,0);
						x=levelGrid.getGridWidth()-1;
					}else{
						square = levelGrid.getGridSquare(0,0);
					}
					int max=levelGrid.getGridHeight();
					int i=1;
					if(shiftingUp){
						//if we are also moving up, skip the corners, the other loop will add them!
						max--;
					}
					if(!shiftingDown){
						//if we are shifting down, we don't want to add the corners, the other loop will do that!
						loadTile(square);
					}
					for(;i<max;i++){
						//System.out.println("LEFT/RIGHT: ("+x+","+i+")");
						square=square.getBelow();
						loadTile(square);
					}
					if(showOverlay){
						screenObjects.remove(overlay);
						screenObjects.addToTop(overlay);
					}
				}
				if(shiftingUp||shiftingDown){
					LevelGridSquare square;
					int y=0;
					if(shiftingUp){
						square = levelGrid.getGridSquare(0,levelGrid.getGridHeight()-1);
						y=levelGrid.getGridHeight()-1;
					}else{
						square = levelGrid.getGridSquare(0,0);
					}
					loadTile(square);
					int max=levelGrid.getGridWidth();
					int i=1;
					for(;i<max;i++){
						//System.out.println("UP/DOWN: ("+i+","+y+")");
						square=square.getRight();
						loadTile(square);
					}
					if(showOverlay){
						screenObjects.remove(overlay);
						screenObjects.addToTop(overlay);
					}
				}
				shiftingLeft=false;
				shiftingRight=false;
				shiftingUp=false;
				shiftingDown=false;
			}
			
			wait(1);//pause
			//tick-tock counter incrementing
			if(counter<1000){
				counter++;
			}else{
				counter=1;
			}
		}
	}
	private void shift(int counter,int numSteps,int gridX,int gridY){
		LevelGridSquare curLoc=levelGrid.getGridSquare(gridX, gridY);
		LevelGridSquare nextLoc=curLoc;

		if(shiftingLeft){
			if(gridX!=0){
				nextLoc=nextLoc.getLeft();
			}else{
				this.clearObjects(curLoc);
			}
		}else if(shiftingRight){
			if(gridX!=levelGrid.getGridWidth()-1){
				nextLoc=nextLoc.getRight();
			}else{
				this.clearObjects(curLoc);
			}
		}
		if(shiftingUp){
			if(gridY!=0){
				nextLoc=nextLoc.getAbove();
			}else{
				this.clearObjects(curLoc);
			}
		}else if(shiftingDown){
			if(gridY!=levelGrid.getGridHeight()-1){
				nextLoc=nextLoc.getBelow();
			}else{
				this.clearObjects(curLoc);
			}
		}
		//System.out.println("("+i+", "+j+")");
		
		//get total distance for all components
		double topLeftDistX=(nextLoc.getTopLeft().getX()-curLoc.getTopLeft().getX())/((double)numSteps);
		double topLeftDistY=(nextLoc.getTopLeft().getY()-curLoc.getTopLeft().getY())/((double)numSteps);
		double topRightDistX=(nextLoc.getTopRight().getX()-curLoc.getTopRight().getX())/((double)numSteps);
		double topRightDistY=(nextLoc.getTopRight().getY()-curLoc.getTopRight().getY())/((double)numSteps);
		double bottomLeftDistX=(nextLoc.getBottomLeft().getX()-curLoc.getBottomLeft().getX())/((double)numSteps);
		double bottomLeftDistY=(nextLoc.getBottomLeft().getY()-curLoc.getBottomLeft().getY())/((double)numSteps);
		double bottomRightDistX=(nextLoc.getBottomRight().getX()-curLoc.getBottomRight().getX())/((double)numSteps);
		double bottomRightDistY=(nextLoc.getBottomRight().getY()-curLoc.getBottomRight().getY())/((double)numSteps);
		
		//System.out.println(curLoc.getObjects().size());
		for(int k=0;k<curLoc.getObjects().size();k++){
			LevelGridSquare realLoc=curLoc.getObjects().get(k).getGridSquare();
			
			if(counter==1000){
				LevelGridDrawable object=curLoc.getObjects().get(k);
				curLoc.getObjects().remove(object);
				nextLoc.getObjects().add(object);
				object.setGridSquare(nextLoc.copy());
			}else{
				realLoc.getTopLeft().setX(realLoc.getTopLeft().getRealX()+topLeftDistX);
				realLoc.getTopLeft().setY(realLoc.getTopLeft().getRealY()+topLeftDistY);
				realLoc.getTopRight().setX(realLoc.getTopRight().getRealX()+topRightDistX);
				realLoc.getTopRight().setY(realLoc.getTopRight().getRealY()+topRightDistY);
				realLoc.getBottomLeft().setX(realLoc.getBottomLeft().getRealX()+bottomLeftDistX);
				realLoc.getBottomLeft().setY(realLoc.getBottomLeft().getRealY()+bottomLeftDistY);
				realLoc.getBottomRight().setX(realLoc.getBottomRight().getRealX()+bottomRightDistX);
				realLoc.getBottomRight().setY(realLoc.getBottomRight().getRealY()+bottomRightDistY);
			}
			//System.out.println(counter+": ( ("+
			//		realLoc.getTopLeft().getRealX()+","+realLoc.getTopLeft().getRealY()+"), ("+
			//		realLoc.getTopRight().getRealX()+","+realLoc.getTopRight().getRealY()+"), ("+
			//		realLoc.getBottomLeft().getRealX()+","+realLoc.getBottomLeft().getRealY()+"), ("+
			//		realLoc.getBottomRight().getRealX()+","+realLoc.getBottomRight().getRealY()+") )");
		}
	}
	private void clearObjects(LevelGridSquare square){
		//check above to see if we need to inform walls to render their front
		try{
			LevelGridSquare above=square.getAbove();
			for(LevelGridDrawable d : above.getObjects()){
				if(d instanceof WallTile){
					((WallTile)d).setRenderFront(true);
				}
			}
		}catch(Exception e){
		}
		//check left to see if we need to inform walls to render their right
		try{
			LevelGridSquare left=square.getLeft();
			for(LevelGridDrawable d : left.getObjects()){
				if(d instanceof WallTile){
					((WallTile)d).setRenderRight(true);
				}
			}
		}catch(Exception e){
		}
		//check right to see if we need to inform walls to render their left
		try{
			LevelGridSquare right=square.getRight();
			for(LevelGridDrawable d : right.getObjects()){
				if(d instanceof WallTile){
					((WallTile)d).setRenderLeft(true);
				}
			}
		}catch(Exception e){
		}
		
		//clear the objects
		ArrayList<LevelGridDrawable> objects = square.getObjects();
		for(LevelGridDrawable d : objects){
			if(d instanceof WallTile){
				topWallTile-=1;
			}else{
				topFloorTile-=1;
				topWallTile-=1;
			}
			screenObjects.remove(d);
		}
		objects.clear();
	}
	private void loadTile(LevelGridSquare square){
		Random rand=new Random();
		LevelGridDrawable tile;
		if(rand.nextInt(4)==0){
			tile=new WallTile(square,new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
			topWallTile+=1;
			
			int pos=topWallTile;
			
			//check below to see if we need to render the front
			try{
				LevelGridSquare below=square.getBelow();
				//Also See if there is a wall segment below to the left that we must render before
				try{
					for(LevelGridDrawable d : below.getLeft().getObjects()){
						if(d instanceof WallTile){
							int i=screenObjects.lastIndexOf(d);
							if(i<pos){
								pos=i;
							}
						}
					}
				}catch(Exception e){
				}
				//Also See if there is a wall segment below to the right that we must render before
				try{
					for(LevelGridDrawable d : below.getRight().getObjects()){
						if(d instanceof WallTile){
							int i=screenObjects.lastIndexOf(d);
							if(i<pos){
								pos=i;
							}
						}
					}
				}catch(Exception e){
				}
				for(LevelGridDrawable d : below.getObjects()){
					if(d instanceof WallTile){
						((WallTile)tile).setRenderFront(false);
						break;
					}
				}
			}catch(Exception e){
			}
			//check above to see if we need to inform walls to not render their front
			try{
				LevelGridSquare above=square.getAbove();
				for(LevelGridDrawable d : above.getObjects()){
					if(d instanceof WallTile){
						((WallTile)d).setRenderFront(false);
					}
				}
			}catch(Exception e){
			}
			//check left to see if we need to render the left and inform walls to not render their right
			try{
				LevelGridSquare left=square.getLeft();
				for(LevelGridDrawable d : left.getObjects()){
					if(d instanceof WallTile){
						((WallTile)d).setRenderRight(false);
						((WallTile)tile).setRenderLeft(false);
					}
				}
			}catch(Exception e){
			}
			//check right to see if we need to render the right and inform walls to not render their left
			try{
				LevelGridSquare right=square.getRight();
				for(LevelGridDrawable d : right.getObjects()){
					if(d instanceof WallTile){
						((WallTile)d).setRenderLeft(false);
						((WallTile)tile).setRenderRight(false);
					}
				}
			}catch(Exception e){
			}
			screenObjects.addAtIndex(pos,tile);
		}else{
			tile=new FloorTile(square,new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
			topFloorTile+=1;
			topWallTile+=1;
			screenObjects.addAtIndex(topFloorTile,tile);
		}
		square.getObjects().add(tile);
	}
	@SuppressWarnings("static-access")
	public static void wait(int millis){
		try {
			Thread.currentThread().sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
