package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import net.brwyatt.brge.graphics.drawables.Drawable;

public class BackgroundGrid implements Drawable {
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
		for(int startX=-350;startX<=1150;startX+=100){
			g.drawLine(startX, 600, vanishingX, vanishingY);
		}//First and last can't be seen, but are still boundary lines (They border empty space that CAN be seen)

		//horizontal lines
		g.drawLine(0, 600, 800, 600);
		
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
