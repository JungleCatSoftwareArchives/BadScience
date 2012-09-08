package net.brwyatt.badscience;

import java.util.ArrayList;

public class LevelGrid {
	private final int vanishingY=1000;//Constant as changing this would break the horizontal line spacing (for now).
	private final int squareWidth=100;//Constant for now. This might be something that can be changed if the viewport size changes
	
	private int viewWidth;
	private int viewHeight;
	private ArrayList<ArrayList<LevelGridSquare>> gridSquares;
	
	public LevelGrid(int viewWidth,int viewHeight){
		this.viewWidth=viewWidth;
		this.viewHeight=viewHeight;
		gridSquares=new ArrayList<ArrayList<LevelGridSquare>>();
		calculateGrid();
	}
	public void calculateGrid(){
		int verticalCount=0;
		int horizontalCount=0;
		
		int prevYPos=0;
		int yPos=0;
		//loop over all the horizontal values (only need to calculate these once
		for(int d=-1;prevYPos>=0;d++){
			prevYPos=yPos;
			if(d==0){
				yPos=viewHeight;
			}else{
				yPos=(int) Math.round((viewHeight-(squareWidth*(48/((Math.sqrt(1872)/d)+3)))));
			}
			horizontalCount++;
			
			//int leftX = (int)Math.round((yPos-leftB)/leftM);
			//System.out.println("LEFT: ("+leftX+","+yPos+")");
			
			//int rightX = (int)Math.round((yPos-rightB)/rightM);
			//System.out.println("RIGHT: ("+rightX+","+yPos+")");
		}
		
		ArrayList<LevelGridSquare> leftCol=null;
		ArrayList<LevelGridSquare> rightCol=null;
		int centerX=viewWidth/2;
		//Find all grid points
		//Loop over vertical lines
		for(int offset=squareWidth/2;offset<=750;offset+=squareWidth){//Test should be changed to determine if we have have found the second vertical that isn't visible
			double leftM=((vanishingY-viewHeight)/((double)offset));
			double leftB=(vanishingY-(leftM*centerX));
			System.out.println("LEFT: y=("+leftM+")*x+("+leftB+")");
			ArrayList<LevelGridPoint> leftList=new ArrayList<LevelGridPoint>();
			verticalCount++;
			
			double rightM=((vanishingY-viewHeight)/(-((double)offset)));
			double rightB=(vanishingY-(rightM*centerX));
			System.out.println("RIGHT: y=("+rightM+")*x+("+rightB+")");
			ArrayList<LevelGridPoint> rightList=new ArrayList<LevelGridPoint>();
			verticalCount++;
			
			lastLineLeft=leftList;
			lastLineRight=rightList;
		}
		lastLineLeft=null;
		lastLineRight=null;
		
		System.out.println("Vertical lines: "+verticalCount);
		System.out.println("Horizontal lines: "+horizontalCount);
	}
	private ArrayList<LevelGridSquare> buildGridSquaresFromLines(ArrayList<LevelGridPoint> leftLine,ArrayList<LevelGridPoint> rightLine){
		ArrayList<LevelGridSquare> gridSquares=new ArrayList<LevelGridSquare>();
		
		for(int i=0;i<leftLine.size()-1&&i<rightLine.size()-1;i++){
			gridSquares.add(new LevelGridSquare(leftLine.get(i),rightLine.get(i),leftLine.get(i+1),rightLine.get(i+1)));
		}
		
		return gridSquares;
	}
	public int getViewWidth() {
		return viewWidth;
	}
	public int getViewHeight() {
		return viewHeight;
	}
	public void setViewWidth(int viewWidth) {
		this.viewWidth = viewWidth;
	}
	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}
}
