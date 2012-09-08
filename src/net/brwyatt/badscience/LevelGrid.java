package net.brwyatt.badscience;

public class LevelGrid {
	private final int vanishingY=1000;//Constant as changing this would break the horizontal line spacing (for now).
	private final int squareWidth=100;//Constant for now. This might be something that can be changed if the viewport size changes
	
	private int viewWidth;
	private int viewHeight;
	
	public LevelGrid(int viewWidth,int viewHeight){
		this.viewWidth=viewWidth;
		this.viewHeight=viewHeight;
	}
	public void calculateGrid(){
		int centerX=viewWidth/2;
		
		//Find all grid points
		//Loop over vertical lines
		for(int offset=squareWidth/2;offset<=750;offset+=squareWidth){//Test should be changed to determine if we have have found the second vertical that isn't visible
			double leftM=((vanishingY-viewHeight)/((double)offset));
			double leftB=(vanishingY-(leftM*centerX));
			System.out.println("LEFT: y=("+leftM+")*x+("+leftB+")");
			
			double rightM=((vanishingY-viewHeight)/(-((double)offset)));
			double rightB=(vanishingY-(rightM*centerX));
			System.out.println("RIGHT: y=("+rightM+")*x+("+rightB+")");

			int PrevYPos=0;
			int yPos=0;
			//loop over horizontal lines
			for(int d=-1;PrevYPos>=0;d++){
				PrevYPos=yPos;
				if(d==0){
					yPos=viewHeight;
				}else{
					yPos=(int) Math.round((viewHeight-(squareWidth*(48/((Math.sqrt(1872)/d)+3)))));
				}
				
				int leftX = (int)Math.round((yPos-leftB)/leftM);
				System.out.println("LEFT: ("+leftX+","+yPos+")");
				
				int rightX = (int)Math.round((yPos-rightB)/rightM);
				System.out.println("RIGHT: ("+rightX+","+yPos+")");
			}
		}
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
