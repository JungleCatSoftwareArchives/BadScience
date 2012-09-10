package net.brwyatt.badscience.levels.levels;

import java.awt.Color;
import java.awt.event.KeyEvent;

import net.brwyatt.badscience.drawables.GridOverlay;
import net.brwyatt.badscience.drawables.FloorTile;
import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.Game;
import net.brwyatt.badscience.drawables.PauseMenuOverlayBackground;
import net.brwyatt.badscience.levelgrid.LevelGrid;
import net.brwyatt.brge.graphics.ScreenObjects;
import net.brwyatt.brge.graphics.drawables.BlackBackground;
import net.brwyatt.brge.graphics.drawables.MenuItem;
import net.brwyatt.brge.levels.Level;


public class TestLevel extends Level{
	
	private boolean run=true;
	private boolean pause=false;
	private Thread t;
	private FloorTile floorTile1;
	private FloorTile floorTile2;
	private FloorTile floorTile3;
	private GridOverlay overlay;
	private boolean showOverlay;
	
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
		floorTile1=new FloorTile(levelGrid.getGridSquare(7, 2),Color.YELLOW);
		screenObjects.addToTop(floorTile1);
		floorTile2=new FloorTile(levelGrid.getGridSquare(3, 8),Color.BLUE);
		screenObjects.addToTop(floorTile2);
		floorTile3=new FloorTile(levelGrid.getGridSquare(3, 1),Color.GREEN);
		screenObjects.addToTop(floorTile3);
		overlay=new GridOverlay(levelGrid);
		showOverlay=true;
		screenObjects.addToTop(overlay);
		
		//for(int y=0;y<600;y+=50){
		//	for(int x=0;x<800;x+=50){
		//		screenObjects.addToTop(new FloorTile(levelGrid.getGridSquare(x, y)));
		//	}
		//}

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
				break;
			case KeyEvent.VK_RIGHT:
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
				break;
			case KeyEvent.VK_RIGHT:
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
		boolean down=true;
		int x1=7;
		int y1=2;
		boolean right=true;
		int x2=3;
		int y2=8;
		boolean downright=true;
		int x3=3;
		int y3=1;
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
			
			/*
			if(counter==1){
				//tile1
				if(down){
					y1++;
				}else{
					y1--;
				}
				if(y1>=8){
					down=false;
				}else{
					if(y1<=2){
						down=true;
					}
				}
				floorTile1.transitionGridSquare(levelGrid.getGridSquare(x1, y1));
				
				//tile2
				if(right){
					x2++;
				}else{
					x2--;
				}
				if(x2>=11){
					right=false;
				}else{
					if(x2<=3){
						right=true;
					}
				}
				floorTile2.transitionGridSquare(levelGrid.getGridSquare(x2, y2));

				//tile3
				if(downright){
					y3++;
					x3++;
				}else{
					y3--;
					x3--;
				}
				if(x3>=10||y3>=8){
					downright=false;
				}else{
					if(x3<=3||y3<=1){
						downright=true;
					}
				}
				floorTile3.transitionGridSquare(levelGrid.getGridSquare(x3, y3));
			}
			*/
			
			wait(1);//pause
			//tick-tock counter incrementing
			if(counter<1000){
				counter++;
			}else{
				counter=1;
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public static void wait(int millis){
		try {
			Thread.currentThread().sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
