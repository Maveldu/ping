import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.util.List;

 
public class Fenetre extends JFrame {
	private Pano pan = new Pano();
	Dimension2D screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	public Fenetre(){
		this.setTitle("Ping");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setContentPane(pan);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
	}
	public void affiche(int n, List<String> p){
		pan.NmbresAffiches=n;
		pan.pings=p;
		pan.repaint();
	}
}