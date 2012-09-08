package net.brwyatt.badscience;

public class LevelGridSquare {
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
}
