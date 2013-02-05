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

package com.junglecatsoftware.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import com.junglecatsoftware.brge.BRGE;
import com.junglecatsoftware.brge.graphics.drawables.LevelGridDrawable;
import com.junglecatsoftware.brge.levelgrid.LevelGridPoint;
import com.junglecatsoftware.brge.levelgrid.LevelGridSquare;


public class WallTile implements LevelGridDrawable {
	private LevelGridSquare levelGridSquare;
	private Color bgColor;
	
	private boolean renderLeft;
	private boolean renderRight;
	private boolean renderFront;
	private boolean renderTop;
	
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
		this.renderLeft=true;
		this.renderRight=true;
		this.renderFront=true;
		this.renderTop=true;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();

		int xScale=10;
		int yScaleTop=2;
		int yScaleBottom=3;
		
		//get bottom grid vertices
		LevelGridPoint bottomFrontLeft=levelGridSquare.getBottomLeft();
		LevelGridPoint bottomFrontRight=levelGridSquare.getBottomRight();
		LevelGridPoint bottomBackLeft=levelGridSquare.getTopLeft();
		LevelGridPoint bottomBackRight=levelGridSquare.getTopRight();
		
		//find top vertices
		int bottomFrontDistance=bottomFrontRight.getX()-bottomFrontLeft.getX();
		LevelGridPoint topFrontLeft=bottomFrontLeft.copy();
		topFrontLeft.setX(topFrontLeft.getX()+((topFrontLeft.getX()-(BRGE.getWidth()/2))/xScale));
		topFrontLeft.setY(topFrontLeft.getY()-(bottomFrontDistance*yScaleTop/yScaleBottom));
		LevelGridPoint topFrontRight=bottomFrontRight.copy();
		topFrontRight.setX(topFrontRight.getX()+((topFrontRight.getX()-(BRGE.getWidth()/2))/xScale));
		topFrontRight.setY(topFrontRight.getY()-(bottomFrontDistance*yScaleTop/yScaleBottom));
		int bottomBackDistance=bottomBackRight.getX()-bottomBackLeft.getX();
		LevelGridPoint topBackLeft=bottomBackLeft.copy();
		topBackLeft.setX(topBackLeft.getX()+((topBackLeft.getX()-(BRGE.getWidth()/2))/xScale));
		topBackLeft.setY(topBackLeft.getY()-(bottomBackDistance*yScaleTop/yScaleBottom));
		LevelGridPoint topBackRight=bottomBackRight.copy();
		topBackRight.setX(topBackRight.getX()+((topBackRight.getX()-(BRGE.getWidth()/2))/xScale));
		topBackRight.setY(topBackRight.getY()-(bottomBackDistance*yScaleTop/yScaleBottom));
		
		if(renderFront){
			//draw front
			g.setColor(bgColor);
			LevelGridSquare front=new LevelGridSquare(topFrontLeft,topFrontRight,bottomFrontLeft,bottomFrontRight);
			g.fillPolygon(front.getPolygon());
			g.setColor(Color.BLACK);
			g.drawPolygon(front.getPolygon());
		}
		
		if(renderTop){
			//draw top
			g.setColor(bgColor);
			LevelGridSquare top=new LevelGridSquare(topBackLeft,topBackRight,topFrontLeft,topFrontRight);
			g.fillPolygon(top.getPolygon());
			g.setColor(Color.BLACK);
			g.drawPolygon(top.getPolygon());
		}
		
		if(renderRight && (levelGridSquare.getBottomRight().getX()<BRGE.getWidth()/2)){
			//draw right
			g.setColor(bgColor);
			LevelGridSquare right=new LevelGridSquare(topFrontRight,topBackRight,bottomFrontRight,bottomBackRight);
			g.fillPolygon(right.getPolygon());
			g.setColor(Color.BLACK);
			g.drawPolygon(right.getPolygon());
		}else if(renderLeft && (levelGridSquare.getBottomLeft().getX()>BRGE.getWidth()/2)){
			//draw left
			g.setColor(bgColor);
			LevelGridSquare left=new LevelGridSquare(topBackLeft,topFrontLeft,bottomBackLeft,bottomFrontLeft);
			g.fillPolygon(left.getPolygon());
			g.setColor(Color.BLACK);
			g.drawPolygon(left.getPolygon());
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
	public void setRenderLeft(boolean render){
		renderLeft=render;
	}
	public void setRenderRight(boolean render){
		renderRight=render;
	}
	public void setRenderFront(boolean render){
		renderFront=render;
	}
	public void setRenderTop(boolean render){
		renderTop=render;
	}
}
