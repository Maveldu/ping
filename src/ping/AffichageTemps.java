package ping;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Panneau affichant la liste des temps de ping
 * @author Mavel
 */

public class AffichageTemps extends JPanel {
	
	// Nombre de temps de ping affichés
	int nombreAffiches = 0;
	
	// Decalage droite de l'affichage
	int decalageDroite;
	
	// Decalage haut de l'affichage
	int decalageHaut;
	
	// Liste des temps à afficher
	List<String> pingsList = new ArrayList<String>();
	
	// Recupération de la résolution de l'écran
	Dimension2D screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public AffichageTemps(int decalageDroite, int decalageHaut) {
		
		this.decalageDroite = decalageDroite;
		this.decalageHaut = decalageHaut;
		
		// L'arrière plan sera transparent
		Color col = new Color(0,0,1,0);
		this.setBackground(col);
	}
	
	public void paintComponent(Graphics g){
		// Affichage de la plupart des temps de ping
		for (int i=0;i<(nombreAffiches-3);i++){
			
			// Ou utilise la couleur rouge
			Color coltxt = new Color(172,0,0,255);
			g.setColor(coltxt);
			// Affichage du temps sur l'écran
			g.drawString(pingsList.get(i), decalageDroite, height-(decalageHaut+14*i));
		}
		// Affichage des trois derniers temps en semi-transparence 
		if(nombreAffiches >= 3){
			
			// On utilise un rouge semi-transparent
			Color col = new Color(172,0,0,50);
			g.setColor(col);
			// Affichage du temps sur l'écran
			g.drawString(pingsList.get(nombreAffiches-1), decalageDroite, height-(decalageHaut+14*(nombreAffiches-1)));
			
			// On utilise un rouge semi-transparent
			Color col2 = new Color(172,0,0,100);
			g.setColor(col2);
			// Affichage du temps sur l'écran
			g.drawString(pingsList.get(nombreAffiches-2), decalageDroite, height-(decalageHaut+14*(nombreAffiches-2)));
			
			// On utilise un rouge semi-transparent
			Color col3 = new Color(172,0,0,150);
			g.setColor(col3);
			// Affichage du temps sur l'écran
			g.drawString(pingsList.get(nombreAffiches-3), decalageDroite, height-(decalageHaut+14*(nombreAffiches-3)));
		}
	}

	public void setNombresAffiches(int n){
		nombreAffiches = n;
	}
	
	public void setPingsList(List<String> p){
		pingsList = p;
	}

}