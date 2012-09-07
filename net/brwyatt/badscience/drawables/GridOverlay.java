package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import net.brwyatt.brge.graphics.drawables.Drawable;

public class GridOverlay implements Drawable {
	private int width=800;
	private int height=600;

	private int x=0;
	private int y=0;

	private int vanishingX=400;
	private int vanishingY=-1000;

	private Color lineColor=Color.WHITE;
	private Color boxColor=Color.RED;

	public void draw(Graphics g) {
		Color tmp=g.getColor();
		
		g.setColor(lineColor); //set color
		
		//vertical lines
		int centerX=width/2;
		for(int offset=50;offset<=650;offset+=100){
			g.drawLine(centerX+offset, 600, vanishingX, vanishingY);
			g.drawLine(centerX-offset, 600, vanishingX, vanishingY);
		}//First and last can't be seen, but are still boundary lines (They border empty space that CAN be seen)

		//horizontal lines
		double scale=0.95;
		int boxHeight=100;
		int pos=600;
		while(pos>0){
			g.drawLine(0, pos, 800, pos);
			boxHeight*=scale;
			pos-=boxHeight;
		}
		
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
