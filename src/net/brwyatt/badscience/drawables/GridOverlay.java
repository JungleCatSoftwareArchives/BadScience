package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import net.brwyatt.brge.graphics.drawables.Drawable;
import net.brwyatt.brge.levelgrid.LevelGrid;
import net.brwyatt.brge.levelgrid.LevelGridSquare;

public class GridOverlay implements Drawable {
	private int width=800;
	private int height=600;
	private LevelGrid levelGrid;

	private int x=0;
	private int y=0;

	private Color lineColor=Color.WHITE;
	private Color boxColor=Color.RED;
	private Color dotColor=Color.BLUE;
	
	public GridOverlay(LevelGrid levelGrid){
		this.levelGrid=levelGrid;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();
		
		g.setColor(lineColor); //set color
		for(int i=0;i<levelGrid.getGridWidth();i++){
			for(int j=0;j<levelGrid.getGridHeight();j++){
				g.drawPolygon(levelGrid.getGridSquare(i, j).getPolygon());
			}
		}
		
		LevelGridSquare centerSquare=levelGrid.getGridSquare((levelGrid.getGridWidth()-1)/2,(levelGrid.getGridHeight())/2);
		g.drawLine(centerSquare.getTopLeft().getX(), centerSquare.getTopLeft().getY(), centerSquare.getBottomRight().getX(), centerSquare.getBottomRight().getY());
		g.drawLine(centerSquare.getTopRight().getX(), centerSquare.getTopRight().getY(), centerSquare.getBottomLeft().getX(), centerSquare.getBottomLeft().getY());
		
		//draw center reference box
		g.setColor(boxColor);
		g.drawRect(350,250,100,100);
		
		g.setColor(tmp); //reset color
	}
	public void setWidth(int w) {
	}
	public void setHeight(int h) {
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setX(int x) {
	}
	public void setY(int y) {
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
