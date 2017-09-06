package ping;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.util.List;

/**
 * Fenetre destin�e � contenir l'afficheur des temps de ping
 * Simple JFrame transparent
 * @author Mavel
 */
public class Fenetre extends JFrame {
	
	// Panneau affichant les temps de ping
	private AffichageTemps pan = new AffichageTemps();
	
	// R�cup�ration de la largeur et de la hauteur de l'�cran
	Dimension2D screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public Fenetre(){
		
		// Initialisations habituelles
		this.setTitle("Ping");
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		// Le programme se fermera � la fermeture de la fenetre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// La fenetre n'aura pas de cadre
		this.setUndecorated(true);
		
		// Son contenu sera l'affichage des temps de ping
		this.setContentPane(pan);
		
		// La fenetre sera toujours au premier plan � l'�cran
		this.setAlwaysOnTop(true);
		
		// Ouverture de la fenetre
		this.setVisible(true);
	}
	
	// Actualisation de l'affichage
	public void affiche(int n, List<String> p){
		// Actualise l'affichage des pings
		pan.setNombresAffiches(n);
		pan.setPingsList(p);
		pan.repaint();
	}
}