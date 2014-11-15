import java.applet.*;
import java.awt.*;
import java.awt.image.*;

public class Average extends Applet{
	
	Image img;
	BufferedImage bimg;
	BufferedImage write;
	int w = 0; //元になるイメージの横幅を代入する
	int h = 0; //元になるイメージの縦幅を代入する
	
	public void init(){
	
		img = getImage(getCodeBase(),"test1.jpg");
		
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(img,0);
		
		try{
			mt.waitForID(0);
		} catch (InterruptedException e){
			
		}

		//w = img.getWidth(this); //ロードしたイメージの横幅を取得
		//h = img.getHeight(this); //ロードしたイメージの縦幅を取得
		
		Operation();
	}
	
	public void Operation(){
		
		bimg = createBufferedImage(img);
		w = bimg.getWidth();
		h = bimg.getHeight();
		write =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		  for(int y=0;y<h;y++){
	            for(int x=0;x<w;x++){
	            	int c = bimg.getRGB(x, y);
	            	write.setRGB(x,y,c);
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
		g.drawImage(write, 0, 0, this);

	}
}
