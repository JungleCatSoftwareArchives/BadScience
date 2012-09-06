package brwyatt.badscience;

import brwyatt.brge.BRGE;
import brwyatt.badscience.BadScience;
import brwyatt.brge.graphics.ScreenObjects;

public class Main {

	public static void main(String args[]){
		BRGE.start(new BadScience(new ScreenObjects()));
	}

}
