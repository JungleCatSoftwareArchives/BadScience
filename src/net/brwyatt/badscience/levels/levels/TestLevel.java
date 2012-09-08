package net.brwyatt.badscience.levels.levels;

import java.awt.event.KeyEvent;

import net.brwyatt.badscience.LevelGrid;
import net.brwyatt.badscience.drawables.GridOverlay;
import net.brwyatt.badscience.drawables.FloorTile;
import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.Game;
import net.brwyatt.badscience.drawables.PauseMenuOverlayBackground;
import net.brwyatt.brge.graphics.ScreenObjects;
import net.brwyatt.brge.graphics.drawables.BlackBackground;
import net.brwyatt.brge.graphics.drawables.MenuItem;
import net.brwyatt.brge.levels.Level;


public class TestLevel extends Level{
	
	private boolean run=true;
	private boolean pause=false;
	private Thread t;
	
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
		screenObjects.addToTop(new GridOverlay());

		//for(int y=0;y<600;y+=50){
		//	for(int x=0;x<800;x+=50){
		//		screenObjects.addToTop(new FloorTile(x,y));
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
