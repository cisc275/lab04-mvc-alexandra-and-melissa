import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

class View extends JPanel{ // repaint, paint, createImage, frame dimensions 
    
	 final int frameCount = 10;
	    int picNum = 0;
	    BufferedImage[] southEast;
	    BufferedImage[] southWest;
	    BufferedImage[] northEast;
	    BufferedImage[] northWest;
	    
	static int xloc = 0;
    static int yloc = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    final static int frameWidth = 500;
    final static int frameHeight = 300;
    final static int imgWidth = 165;
    final static int imgHeight = 165;

	 static boolean moveLeft = false;
	  static boolean moveUp = false;
	
public View(){
    	
    	BufferedImage se = createImage();
    	southEast = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		southEast[i] = se.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	moveUp = false;
    	moveLeft = true;
    	BufferedImage sw = createImage();
    	southWest = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		southWest[i] = sw.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	moveUp = true;
    	moveLeft = false;
    	BufferedImage ne = createImage();
    	northEast = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		northEast[i] = ne.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	
    	moveUp = true;
    	moveLeft = true;
    	BufferedImage nw = createImage();
    	northWest = new BufferedImage[10];
    	for(int i = 0; i < frameCount; i++)
    		northWest[i] = nw.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
}
    
	public int getXLoc() {
		return View.xloc;
	}
	public int getWidth() {
		return View.frameWidth;
	}
	
	public int getHeight() {
		return View.frameHeight;
	}
	
	public int getImageWidth() {
		return View.imgWidth;
	}
	
	public int getImageHeight() {
		return View.imgHeight;
	}
	

	    //Override this JPanel's paint method to cycle through picture array and draw images
	    public void paint(Graphics g) {		

	    	picNum = (picNum + 1) % frameCount;
	    	Model.updateLocationAndDirection();
	    	
	    	if ((moveLeft == false)&& (moveUp == false)) { // hit top left 
	    		g.drawImage(southEast[picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
	    	}
	    	else if ((moveLeft == true) && (moveUp == false)) { // hit top right 
	    		g.drawImage(southWest[picNum], xloc-=xIncr, yloc+=yIncr, Color.gray, this);
	    	}
	    	else if ((moveLeft == false) && (moveUp == true)) { // hit bottom left 
	    		g.drawImage(northEast[picNum], xloc+=xIncr, yloc-=yIncr, Color.gray, this);
	    	}
	    	else if ((moveLeft == true) && (moveUp == true)) {
	    		g.drawImage(northWest[picNum], xloc-=xIncr, yloc-=yIncr, Color.gray, this);
	    	}
	    	else {
	    		g.drawImage(southEast[picNum], xloc-=xIncr, yloc+=yIncr, Color.gray, this);
	    	
	    	}
	    }
	    
	    
	    private BufferedImage createImage(){
	    	
	    	BufferedImage bufferedImage;
	    	BufferedImage bufferedImage2;
	    	BufferedImage bufferedImage3;
	    	BufferedImage bufferedImage4;
	    	
	    	
	    	if (moveUp == false && moveLeft == false) {
		    	try {
		    		bufferedImage = ImageIO.read(new File("src/images/orc_forward_southeast.png"));
		    		return bufferedImage;  		
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
	    	
	    	} else if (moveUp == false && moveLeft == true) {
	    		
	    		try {
	        		bufferedImage2 = ImageIO.read(new File("src/images/orc_forward_southwest.png"));
	        		
	        		return bufferedImage2;
	        		
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	    	}
	    	else if (moveUp == true && moveLeft == false) {

	    		try {
	        		bufferedImage3 = ImageIO.read(new File("src/images/orc_forward_northeast.png"));
	        		return bufferedImage3;
	        		
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	    	}
	    	else if (moveUp == true && moveLeft == true) {

	    		try {
	        		bufferedImage4 = ImageIO.read(new File("src/images/orc_forward_northwest.png"));
	        		
	        		return bufferedImage4;
	        		
	        	} catch (IOException e) {
	        		e.printStackTrace();
	        	}
	    	}
	    	return null;
	    }
	    
	    public void update(int x, int y, Direction direction) {
	    	xloc = x;
	    	yloc = y;
	    	if(direction == Direction.NORTHEAST) {
	    		moveLeft = false;
	    		moveUp = true;
	    	}
	    	else if(direction == Direction.NORTHWEST) {
	    		moveLeft = true;
	    		moveUp = true;
	    	}
	    	else if(direction == Direction.SOUTHEAST) {
	    		moveLeft = false;
	    		moveUp = false;
	    	}else {
	    		moveLeft = true;
	    		moveUp = false;
	    	}
	    	
	    }
	    
	    public static void main(String[] args) {
	    	
	    	JFrame frame = new JFrame();
	    	frame.getContentPane().add(new View());
	    	frame.setBackground(Color.gray);
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.setSize(frameWidth, frameHeight);
	    	frame.setVisible(true);
	    	Controller cont = new Controller();
	    	cont.start();
	    	
	    	for(int i = 0; i < 1000; i++){
	    		frame.repaint();
	    		try {
	    			Thread.sleep(100);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	
	    }
}
