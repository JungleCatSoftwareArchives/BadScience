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
	private Color dotColor=Color.BLUE;

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
		int yPos=600;
		for(int d=-1;yPos>=0;d++){
			if(d==0){
				yPos=height;
			}else{
				yPos=(int) Math.round((height-(100*(48/((Math.sqrt(1872)/d)+3)))));
			}
			g.drawLine(0, yPos, 800, yPos);
		}
		
		
		//Find all intersections
		//System.out.println("*******************");
		g.setColor(dotColor); //set color
		centerX=width/2;
		for(int offset=50;offset<=650;offset+=100){
			//(400,-1000)
			
			// y = (offset/(vanishingY-height))*x + (vanishingY-((offset/(vanishingY-height))*vanishingX)
			double leftM=((vanishingY-height)/((double)offset));
			double leftB=(vanishingY-(leftM*vanishingX));
			//System.out.println("LEFT: y=("+leftM+")*x+("+leftB+")");
			
			// y = ((-offset)/(vanishingY-height))*x + (vanishingY-(((-offset)/(vanishingY-height))*vanishingX)
			double rightM=((vanishingY-height)/(-((double)offset)));
			double rightB=(vanishingY-(rightM*vanishingX));
			//System.out.println("RIGHT: y=("+rightM+")*x+("+rightB+")");
			

			yPos=600;
			for(int d=-1;yPos>=0;d++){
				if(d==0){
					yPos=height;
				}else{
					yPos=(int) Math.round((height-(100*(48/((Math.sqrt(1872)/d)+3)))));
				}
				int leftX = (int)Math.round((yPos-leftB)/leftM);
				//System.out.println("LEFT: ("+leftX+","+yPos+")");
				g.fillRect(leftX-1, yPos-1, 3, 3);
				int rightX = (int)Math.round((yPos-rightB)/rightM);
				//System.out.println("RIGHT: ("+rightX+","+yPos+")");
				g.fillRect(rightX-1, yPos-1, 3, 3);
			}
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
