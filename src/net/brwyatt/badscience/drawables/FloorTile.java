package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import net.brwyatt.badscience.levelgrid.LevelGridPoint;
import net.brwyatt.badscience.levelgrid.LevelGridSquare;
import net.brwyatt.brge.graphics.drawables.Drawable;

public class FloorTile implements Drawable {
	private LevelGridSquare gridSquare;
	private Color bgColor;
	
	public FloorTile(LevelGridSquare gridSquare){
		this(gridSquare,Color.GRAY);
	}
	public FloorTile(LevelGridSquare gridSquare,Color color){
		this(
				new LevelGridPoint(gridSquare.getTopLeft().getRealX(),gridSquare.getTopLeft().getRealY()),
				new LevelGridPoint(gridSquare.getTopRight().getRealX(),gridSquare.getTopRight().getRealY()),
				new LevelGridPoint(gridSquare.getBottomLeft().getRealX(),gridSquare.getBottomLeft().getRealY()),
				new LevelGridPoint(gridSquare.getBottomRight().getRealX(),gridSquare.getBottomRight().getRealY()),
				color);
	}
	public FloorTile(LevelGridPoint topLeft,LevelGridPoint topRight,LevelGridPoint bottomLeft,LevelGridPoint bottomRight){
		this(topLeft, topRight, bottomLeft, bottomRight,Color.GRAY);
	}
	public FloorTile(LevelGridPoint topLeft,LevelGridPoint topRight,LevelGridPoint bottomLeft,LevelGridPoint bottomRight,Color color){
		this.gridSquare=new LevelGridSquare(topLeft,topRight,bottomLeft,bottomRight);
		this.bgColor=color;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();

		g.setColor(bgColor); //set color
		g.fillPolygon(gridSquare.getPolygon());
		/*
		if(nextGridSquare==null){
			g.fillPolygon(this.gridSquare.getPolygon());
		}else{
			//get total distance for all components
			double topLeftDistX=nextGridSquare.getTopLeft().getX()-gridSquare.getTopLeft().getX();
			double topLeftDistY=nextGridSquare.getTopLeft().getY()-gridSquare.getTopLeft().getY();
			double topRightDistX=nextGridSquare.getTopRight().getX()-gridSquare.getTopRight().getX();
			double topRightDistY=nextGridSquare.getTopRight().getY()-gridSquare.getTopRight().getY();
			double bottomLeftDistX=nextGridSquare.getBottomLeft().getX()-gridSquare.getBottomLeft().getX();
			double bottomLeftDistY=nextGridSquare.getBottomLeft().getY()-gridSquare.getBottomLeft().getY();
			double bottomRightDistX=nextGridSquare.getBottomRight().getX()-gridSquare.getBottomRight().getX();
			double bottomRightDistY=nextGridSquare.getBottomRight().getY()-gridSquare.getBottomRight().getY();

			this.transitionGridSquare.getTopLeft().setX(this.transitionGridSquare.getTopLeft().getRealX()+(topLeftDistX/this.stepScale));
			this.transitionGridSquare.getTopLeft().setY(this.transitionGridSquare.getTopLeft().getRealY()+(topLeftDistY/this.stepScale));
			this.transitionGridSquare.getTopRight().setX(this.transitionGridSquare.getTopRight().getRealX()+(topRightDistX/this.stepScale));
			this.transitionGridSquare.getTopRight().setY(this.transitionGridSquare.getTopRight().getRealY()+(topRightDistY/this.stepScale));
			this.transitionGridSquare.getBottomLeft().setX(this.transitionGridSquare.getBottomLeft().getRealX()+(bottomLeftDistX/this.stepScale));
			this.transitionGridSquare.getBottomLeft().setY(this.transitionGridSquare.getBottomLeft().getRealY()+(bottomLeftDistY/this.stepScale));
			this.transitionGridSquare.getBottomRight().setX(this.transitionGridSquare.getBottomRight().getRealX()+(bottomRightDistX/this.stepScale));
			this.transitionGridSquare.getBottomRight().setY(this.transitionGridSquare.getBottomRight().getRealY()+(bottomRightDistY/this.stepScale));
			
			g.fillPolygon(this.transitionGridSquare.getPolygon());
			
			if(nextGridSquare.compareTo(transitionGridSquare)==0){//we made it! reset!
				gridSquare=nextGridSquare;
				nextGridSquare=null;
				transitionGridSquare=null;
			}
		}
		*/
		
		g.setColor(tmp); //reset color
	}
	public void setGridSquare(LevelGridSquare gridSquare){
		this.gridSquare=gridSquare;
	}
	public void setWidth(int w) {
	}
	public void setHeight(int h) {
	}
	public int getWidth() {
		return 0;
	}
	public int getHeight() {
		return 0;
	}
	public void setX(int x) {
	}
	public void setY(int y) {
	}
	public int getX() {
		return 0;
	}
	public int getY() {
		return 0;
	}
}
