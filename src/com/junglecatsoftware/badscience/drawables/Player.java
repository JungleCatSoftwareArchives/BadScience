package com.junglecatsoftware.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import com.junglecatsoftware.brge.BRGE;
import com.junglecatsoftware.brge.graphics.drawables.Drawable;
import com.junglecatsoftware.brge.levelgrid.LevelGridPoint;
import com.junglecatsoftware.brge.levelgrid.LevelGridSquare;

public class Player implements Drawable {
	
	private Color playerColor;
	private LevelGridSquare referenceGridSquare;
	boolean bounceUp=true;
	int bouncy=0;
	
	public Player(){
		this(Color.RED);
	}
	public Player(LevelGridSquare ref){
		this(ref, Color.RED);
	}
	public Player(Color c){
		this(new LevelGridSquare(
				new LevelGridPoint((BRGE.getWidth()/2)-20, (BRGE.getHeight()/2)-20),
				new LevelGridPoint((BRGE.getWidth()/2)+20, (BRGE.getHeight()/2)-20),
				new LevelGridPoint((BRGE.getWidth()/2)-20, (BRGE.getHeight()/2)+20),
				new LevelGridPoint((BRGE.getWidth()/2)+20, (BRGE.getHeight()/2)+20)),
				c);
	}
	public Player(LevelGridSquare ref, Color c){
		playerColor=c;
		referenceGridSquare=ref;
	}

	@Override
	public void draw(Graphics g) {
		Color tmp=g.getColor();
		
		int xScale=10;
		int yScaleTop=2;
		int yScaleBottom=3;
		int scalor=4;
		int realBouncy=bouncy/5;
		int perspectiveAdder=bouncy/20;

		int topDistance=referenceGridSquare.getTopRight().getX()-referenceGridSquare.getTopLeft().getX();
		int topTranslator=topDistance/scalor;
		int bottomDistance=referenceGridSquare.getBottomRight().getX()-referenceGridSquare.getBottomLeft().getX();
		int bottomTranslator=bottomDistance/scalor;
		
		//get bottom grid vertices
		LevelGridPoint bottomFrontLeft=referenceGridSquare.getBottomLeft().copy();
		bottomFrontLeft.setX(bottomFrontLeft.getX()+bottomTranslator);
		bottomFrontLeft.setY(bottomFrontLeft.getY()-bottomTranslator);
		LevelGridPoint bottomFrontRight=referenceGridSquare.getBottomRight().copy();
		bottomFrontRight.setX(bottomFrontRight.getX()-bottomTranslator);
		bottomFrontRight.setY(bottomFrontRight.getY()-bottomTranslator);
		LevelGridPoint bottomBackLeft=referenceGridSquare.getTopLeft().copy();
		bottomBackLeft.setX(bottomBackLeft.getX()+topTranslator);
		bottomBackLeft.setY(bottomBackLeft.getY()+topTranslator);
		LevelGridPoint bottomBackRight=referenceGridSquare.getTopRight().copy();
		bottomBackRight.setX(bottomBackRight.getX()-topTranslator);
		bottomBackRight.setY(bottomBackRight.getY()+topTranslator);
		
		//draw shadow
		g.setColor(new Color((float)0, (float)0, (float)0, (float)0.60));
		LevelGridSquare shadow=new LevelGridSquare(bottomBackLeft,bottomBackRight,bottomFrontLeft,bottomFrontRight);
		g.fillPolygon(shadow.getPolygon());
		
		//translate for player box
		bottomFrontLeft.setX(bottomFrontLeft.getX()-perspectiveAdder);
		bottomFrontLeft.setY(bottomFrontLeft.getY()-realBouncy);
		bottomFrontRight.setX(bottomFrontRight.getX()+perspectiveAdder);
		bottomFrontRight.setY(bottomFrontRight.getY()-realBouncy);
		bottomBackLeft.setX(bottomBackLeft.getX()-perspectiveAdder);
		bottomBackLeft.setY(bottomBackLeft.getY()-realBouncy);
		bottomBackRight.setX(bottomBackRight.getX()+perspectiveAdder);
		bottomBackRight.setY(bottomBackRight.getY()-realBouncy);
		
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
		
		//draw front
		g.setColor(playerColor);
		LevelGridSquare front=new LevelGridSquare(topFrontLeft,topFrontRight,bottomFrontLeft,bottomFrontRight);
		g.fillPolygon(front.getPolygon());
		g.setColor(Color.darkGray);
		g.drawPolygon(front.getPolygon());
		
		//draw top
		g.setColor(playerColor);
		LevelGridSquare top=new LevelGridSquare(topBackLeft,topBackRight,topFrontLeft,topFrontRight);
		g.fillPolygon(top.getPolygon());
		g.setColor(Color.darkGray);
		g.drawPolygon(top.getPolygon());

		if(bounceUp){
			if(bouncy==50){
				bounceUp=false;
			}else{
				bouncy++;
			}
		}else{
			if(bouncy==0){
				bounceUp=true;
			}else{
				bouncy--;
			}
		}
		
		g.setColor(tmp); //reset color
	}

	@Override
	public void setWidth(int w) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHeight(int h) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
