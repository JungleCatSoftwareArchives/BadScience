package net.brwyatt.badscience.levels.levels;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import net.brwyatt.badscience.drawables.GridOverlay;
import net.brwyatt.badscience.drawables.FloorTile;
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

		screenObjects.addToBottom(new BlackBackground());
		
		Random rand=new Random();
		for(int y=0;y<levelGrid.getGridHeight();y+=1){
			for(int x=0;x<levelGrid.getGridWidth();x+=1){
				LevelGridSquare square=levelGrid.getGridSquare(x, y);
				FloorTile tile=new FloorTile(square,new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
				square.getObjects().add(tile);
				screenObjects.addToTop(tile);
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
			
			//Only update 100 times/second, and only when actually moving
			int stepRegulator=10;
			int stepCount=(1000/stepRegulator)-1;
			if((counter%stepRegulator==0)&&(shiftingLeft||shiftingRight||shiftingUp||shiftingDown)){
				if(shiftingRight||(shiftingDown&&(!shiftingLeft))){//use up then left navigation
					for(int i=levelGrid.getGridWidth()-1;i>=0;i--){
						for(int j=levelGrid.getGridHeight()-1;j>=0;j--){
							shift(counter,stepCount,i,j);
						}
					}
				}else{//Use down then right navigation
					for(int i=0;i<levelGrid.getGridWidth();i++){
						for(int j=0;j<levelGrid.getGridHeight();j++){
							shift(counter,stepCount,i,j);
						}
					}
				}
			}
			
			if(counter==1000){//reset shifting
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
				this.clearObjects(curLoc.getObjects());
			}
		}else if(shiftingRight){
			if(gridX!=levelGrid.getGridWidth()-1){
				nextLoc=nextLoc.getRight();
			}else{
				this.clearObjects(curLoc.getObjects());
			}
		}
		if(shiftingUp){
			if(gridY!=0){
				nextLoc=nextLoc.getAbove();
			}else{
				this.clearObjects(curLoc.getObjects());
			}
		}else if(shiftingDown){
			if(gridY!=levelGrid.getGridHeight()-1){
				nextLoc=nextLoc.getBelow();
			}else{
				this.clearObjects(curLoc.getObjects());
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
			System.out.println(counter+": ( ("+
					realLoc.getTopLeft().getRealX()+","+realLoc.getTopLeft().getRealY()+"), ("+
					realLoc.getTopRight().getRealX()+","+realLoc.getTopRight().getRealY()+"), ("+
					realLoc.getBottomLeft().getRealX()+","+realLoc.getBottomLeft().getRealY()+"), ("+
					realLoc.getBottomRight().getRealX()+","+realLoc.getBottomRight().getRealY()+") )");
		}
	}
	private void clearObjects(ArrayList<LevelGridDrawable> objects){
		for(LevelGridDrawable d : objects){
			screenObjects.remove(d);
		}
		objects.clear();
	}
	@SuppressWarnings("static-access")
	public static void wait(int millis){
		try {
			Thread.currentThread().sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
