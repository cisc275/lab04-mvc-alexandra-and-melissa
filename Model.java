/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/


class Model {
	
	static int x;
	static int y;
	Direction direct;

	
	public Model(int x, int y, int imageWidth, int imageHeight) {
		Model.x = View.xloc;
		Model.y = View.yloc;
	}
	
	public int getX() {
		return Model.x;
	}
	
	public int getY() {
		return Model.y;
	}

	public void updateLocationAndDirection() { // if statement to detect collision 
		if (View.xloc >= View.frameWidth - 130) {
			View.moveLeft = true;
		// test hit left
		} else if (View.xloc < -30) {
			View.moveLeft = false;    	
		}
		// test hit bottom
		if (View.yloc >= View.frameHeight - 150) {
			View.moveUp = true;
			
		} // test hit top 
		else if (View.yloc < -30) {
			View.moveUp = false;
		}		
	}

	public Direction getDirect() {
		return this.direct;
	}
}
