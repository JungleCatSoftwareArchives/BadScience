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

import com.junglecatsoftware.brge.graphics.drawables.LevelGridDrawable;
import com.junglecatsoftware.brge.levelgrid.LevelGridPoint;
import com.junglecatsoftware.brge.levelgrid.LevelGridSquare;


public class FloorTile implements LevelGridDrawable {
	private LevelGridSquare levelGridSquare;
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
		this.levelGridSquare=new LevelGridSquare(topLeft,topRight,bottomLeft,bottomRight);
		this.bgColor=color;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();

		g.setColor(bgColor); //set color
		g.fillPolygon(levelGridSquare.getPolygon());
		
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
