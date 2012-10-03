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
