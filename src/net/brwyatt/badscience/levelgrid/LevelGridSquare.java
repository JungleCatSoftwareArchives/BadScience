package net.brwyatt.badscience.levelgrid;

import java.awt.Polygon;

public class LevelGridSquare implements Comparable<LevelGridSquare>{
	private LevelGridPoint topLeft;
	private LevelGridPoint topRight;
	private LevelGridPoint bottomLeft;
	private LevelGridPoint bottomRight;
	
	public LevelGridSquare(LevelGridPoint topLeft,LevelGridPoint topRight,LevelGridPoint bottomLeft,LevelGridPoint bottomRight){
		this.setTopLeft(topLeft);
		this.setTopRight(topRight);
		this.setBottomLeft(bottomLeft);
		this.setBottomRight(bottomRight);
	}

	public LevelGridPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(LevelGridPoint topLeft) {
		this.topLeft = topLeft;
	}

	public LevelGridPoint getTopRight() {
		return topRight;
	}

	public void setTopRight(LevelGridPoint topRight) {
		this.topRight = topRight;
	}

	public LevelGridPoint getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(LevelGridPoint bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	public LevelGridPoint getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(LevelGridPoint bottomRight) {
		this.bottomRight = bottomRight;
	}
	public Polygon getPolygon(){
		Polygon p=new Polygon();
		p.addPoint(topLeft.getX(), topLeft.getY());
		p.addPoint(topRight.getX(), topRight.getY());
		p.addPoint(bottomRight.getX(), bottomRight.getY());
		p.addPoint(bottomLeft.getX(), bottomLeft.getY());
		return p;
	}

	@Override
	public int compareTo(LevelGridSquare other) {
		int diff=0;
		diff+=this.topLeft.getX()-other.topLeft.getX();
		diff+=this.topLeft.getY()-other.topLeft.getY();
		diff+=this.topRight.getX()-other.topRight.getX();
		diff+=this.topRight.getY()-other.topRight.getY();
		diff+=this.bottomLeft.getX()-other.bottomLeft.getX();
		diff+=this.bottomLeft.getY()-other.bottomLeft.getY();
		diff+=this.bottomRight.getX()-other.bottomRight.getX();
		diff+=this.bottomRight.getY()-other.bottomRight.getY();
		return diff;
	}
}
