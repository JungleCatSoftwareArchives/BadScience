package net.brwyatt.badscience.drawables;

import java.awt.Color;
import java.awt.Graphics;

import net.brwyatt.brge.graphics.drawables.Drawable;

public class BackgroundGrid implements Drawable {
	private int width=800;
	private int height=600;

	private int x=0;
	private int y=0;

	private Color lineColor=Color.WHITE;
	private Color dotColor=Color.RED;

	public void draw(Graphics g) {
		Color tmp=g.getColor();
		
		g.setColor(lineColor); //set color

		g.drawLine(-350, 600, 400, -1000); //can't be seen, but is still a boundary line
		g.drawLine(-250, 600, 400, -1000);
		g.drawLine(-150, 600, 400, -1000);
		g.drawLine(-50, 600, 400, -1000);
		g.drawLine(50, 600, 400, -1000);
		g.drawLine(150, 600, 400, -1000);
		g.drawLine(250, 600, 400, -1000);
		g.drawLine(350, 600, 400, -1000);
		g.drawLine(450, 600, 400, -1000);
		g.drawLine(550, 600, 400, -1000);
		g.drawLine(650, 600, 400, -1000);
		g.drawLine(750, 600, 400, -1000);
		g.drawLine(850, 600, 400, -1000);
		g.drawLine(950, 600, 400, -1000);
		g.drawLine(1050, 600, 400, -1000);
		g.drawLine(1150, 600, 400, -1000); //can't be seen, but is still a boundary line

		g.drawLine(0, 600, 800, 600);
		
		g.setColor(dotColor);
		//g.fillRect(399, 599, 3, 3);
		//g.fillRect(399, 499, 3, 3);
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
