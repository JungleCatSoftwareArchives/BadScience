package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.graphics.drawables.LevelGridDrawable;
import net.brwyatt.brge.levelgrid.LevelGridPoint;
import net.brwyatt.brge.levelgrid.LevelGridSquare;

public class WallTile implements LevelGridDrawable {
	private LevelGridSquare levelGridSquare;
	private Color bgColor;
	
	public WallTile(LevelGridSquare gridSquare){
		this(gridSquare,Color.GRAY);
	}
	public WallTile(LevelGridSquare gridSquare,Color color){
		this(
				new LevelGridPoint(gridSquare.getTopLeft().getRealX(),gridSquare.getTopLeft().getRealY()),
				new LevelGridPoint(gridSquare.getTopRight().getRealX(),gridSquare.getTopRight().getRealY()),
				new LevelGridPoint(gridSquare.getBottomLeft().getRealX(),gridSquare.getBottomLeft().getRealY()),
				new LevelGridPoint(gridSquare.getBottomRight().getRealX(),gridSquare.getBottomRight().getRealY()),
				color);
	}
	public WallTile(LevelGridPoint topLeft,LevelGridPoint topRight,LevelGridPoint bottomLeft,LevelGridPoint bottomRight){
		this(topLeft, topRight, bottomLeft, bottomRight,Color.GRAY);
	}
	public WallTile(LevelGridPoint topLeft,LevelGridPoint topRight,LevelGridPoint bottomLeft,LevelGridPoint bottomRight,Color color){
		this.levelGridSquare=new LevelGridSquare(topLeft,topRight,bottomLeft,bottomRight);
		this.bgColor=color;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();

		g.setColor(bgColor); //set color
		LevelGridPoint bottomLeft=levelGridSquare.getBottomLeft().copy();
		LevelGridPoint bottomRight=levelGridSquare.getBottomRight().copy();
		int distance=bottomRight.getX()-bottomLeft.getX();
		LevelGridPoint topLeft=bottomLeft.copy();
		topLeft.setY(topLeft.getY()-distance);
		LevelGridPoint topRight=bottomRight.copy();
		topRight.setY(topRight.getY()-distance);
		LevelGridSquare front=new LevelGridSquare(topLeft,topRight,bottomLeft,bottomRight);
		g.fillPolygon(front.getPolygon());
		g.setColor(Color.BLACK);
		g.drawPolygon(front.getPolygon());
		
		LevelGridSquare side=null;
		if(levelGridSquare.getBottomRight().getX()<BRGE.getWidth()/2){
			
		}else if(levelGridSquare.getBottomLeft().getX()>BRGE.getWidth()/2){
			
		}
		
		if(side!=null){
			
		}
		
		g.setColor(tmp); //reset color
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
	public void setGridSquare(LevelGridSquare levelGridSquare) {
		this.levelGridSquare=levelGridSquare;
	}
	public LevelGridSquare getGridSquare() {
		return this.levelGridSquare;
	}
}