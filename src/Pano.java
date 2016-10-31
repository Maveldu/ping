import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.util.List;


public class Pano extends JPanel {
	int NmbresAffiches = 0;
	List<String> pings;
	Dimension2D screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	public Pano() {
		Color col = new Color(0,0,1,0);
		this.setBackground(col);
	}
	public void paintComponent(Graphics g){
		for (int i=0;i<(NmbresAffiches-3);i++){
			Color coltxt = new Color(172,0,0,255);
			g.setColor(coltxt);
			g.drawString(pings.get(i), 10,height-(50+14*i));
			if (i==NmbresAffiches-4){
				Color col = new Color(172,0,0,25);
				g.setColor(col);
				g.drawString(pings.get(NmbresAffiches-1), 10,height-(50+14*(NmbresAffiches-1)));
				Color col2 = new Color(172,0,0,50);
				g.setColor(col2);
				g.drawString(pings.get(NmbresAffiches-2), 10, (int) (height-(50+14*(NmbresAffiches-2))));
				Color col3 = new Color(172,0,0,100);
				g.setColor(col3);
				g.drawString(pings.get(NmbresAffiches-3), 10,height-(50+14*(NmbresAffiches-3)));
			}
		}
	}
}