package img;

import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class Average extends Applet{
	
	Image img1;
	Image img2;
	BufferedImage bimg1;
	BufferedImage bimg2;
	BufferedImage new_img;
	int w = 0; //元になるイメージの横幅を代入する
	int h = 0; //元になるイメージの縦幅を代入する
	int c1,a1,r1,g1,b1;
	int c2,a2,r2,g2,b2;
	int new_c,a,r,g,b;
	
	public void init(){
	
		img1 = getImage(getCodeBase(),"./pic/test2.jpg");
		img2 = getImage(getCodeBase(),"./pic/test1.jpg");
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img1,0);
		mt.addImage(img2,1);
		
		try{
			mt.waitForAll();
		} catch (InterruptedException e){
			
		}
		
		Operation();
	}
	
	public void Operation(){
		
		bimg1 = createBufferedImage(img1);
		bimg2 = createBufferedImage(img2);
		
		w = bimg1.getWidth();
		h = bimg1.getHeight();
		
		new_img =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		  for(int y=0;y<h;y++){
	            for(int x=0;x<w;x++){
	            	
	            	c1 = bimg1.getRGB(x, y);
	            	a1 = Formatter.a(c1);
	            	r1 = Formatter.r(c1);
	            	g1 = Formatter.g(c1);
	            	b1 = Formatter.b(c1);
	            	
	            	c2 = bimg2.getRGB(x, y);
	            	a2 = Formatter.a(c2);
	            	r2 = Formatter.r(c2);
	            	g2 = Formatter.g(c2);
	            	b2 = Formatter.b(c2);
	            	
	            	 a = (a1 + a2)/2;
	            	 r = (r1 + r2)/2;
	            	 g = (g1 + g2)/2;
	            	 b = (b1 + b2)/2;
	            	 
	            	 new_c = Formatter.argb(a, r, g, b);
	            	
	            	new_img.setRGB(x,y,new_c);
	            }
	      }
		
	}
	
    public static BufferedImage createBufferedImage(Image img)
    {   
    	BufferedImage bimg = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
        Graphics g = bimg.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();
        return bimg;
    }
    
	public void paint(Graphics g){
		g.drawImage(img1,0, 0, this);
		g.drawImage(img2, w+5, 0, this);
		g.drawImage(new_img, 2*w+10, 0, this);

	}
}
