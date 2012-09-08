package net.brwyatt.badscience;

import net.brwyatt.badscience.BadScience;
import net.brwyatt.brge.BRGE;
import net.brwyatt.brge.graphics.ScreenObjects;

public class Main {

	public static void main(String args[]){
		BRGE.start(new BadScience(new ScreenObjects()));
	}

}
