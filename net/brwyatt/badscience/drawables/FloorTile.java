package net.brwyatt.badscience.drawables;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import net.brwyatt.brge.graphics.drawables.Drawable;

public class FloorTile implements Drawable {
	private int width=50;
	private int height=50;
	
	private int x;
	private int y;

	private Color bgColor=Color.GRAY;
	private Color fgColor=Color.DARK_GRAY;
	
	public FloorTile(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void draw(Graphics g) {
		Color tmp=g.getColor();
		Stroke tmpStroke=((Graphics2D)g).getStroke();
		
		g.setColor(bgColor); //set color
		g.fillRect(x, y, width, height);
		g.setColor(fgColor); //set color
		((Graphics2D)g).setStroke(new BasicStroke(2.0f));
		g.drawRect(x, y, width, height);
		
		g.setColor(tmp); //reset color
		((Graphics2D)g).setStroke(tmpStroke);//reset Stroke
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
