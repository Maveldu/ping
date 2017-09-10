package ping;
import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette application calcule le temps de réponse d'un serveur, et l'affiche en bas à gauche de l'écran, en rouge
 * @author Mavel
 */

public class Controleur {
	
	// ----- Paramètres par défaut de l'application -----
	private static byte[] adresse = {8,8,8,8};
	private static int timeout = 10000;
	private static int NombresAffiches = 30;
	private static int decalageDroite = 10;
	private static int decalageHaut = 50;	
	private static int frequence = 500;
	// ---------------------------------------
	
	
	public static void main(String[] args){
		
		// On traite d'abord les arguments passés en ligne de commande
		gererArguments(args);
		
		// Ouverture de la fenetre
	    Fenetre fenetre = new Fenetre(decalageDroite, decalageHaut);
	    // Création de l'objet permettant de faire nos tests
	    Pinger pinger = new Pinger(adresse, timeout);
	    // Initialisation de notre liste de temps de ping
	    List<String> pings = new ArrayList<String>();
	    // Initialisation de notre variable permettant de récupérer nos temps
	    String pingResult;
	    
	    // Arrière plan de la fenetre transparent
	    fenetre.setBackground(new Color(0,0,0,0));
	    
	    // Initialement nos temps seront à 0
	    for (int i=0;i!=NombresAffiches;i++){
	    	pings.add("0");
	    }
	    fenetre.affiche(NombresAffiches, pings);
	    
	    // Boucle infinie principale de l'application
	    while(true){
	    	// On calcule le temps de ping
	    	pingResult = pinger.ping();
	       
	    	// On ajoute ce résultat à notre liste
    		pings.remove(NombresAffiches-1);
    		pings.add(pingResult);
    		
    		// On défile les éléments de la file afin que notre nouvel élément soit positionné à la première place
    		String j="0";
           	for (int i=0;i<NombresAffiches-1;i++){
    			j=pings.get(0);
    			pings.remove(0);
    			pings.add(j);
    		}
           	
           	// On actualise notre affichage avec le nouveau résultat
        	fenetre.affiche(NombresAffiches, pings);
        	
        	// Petit temps afin d'éviter de faire tourner l'application trop vite et de faire des pings trop fréquents
    		try {
    	    	Thread.sleep(frequence);
    	    } catch (InterruptedException e) {
    	    	e.printStackTrace();
    	    }
	    }
	}
	
	private static void gererArguments(String[] args){
		// Pour chaque paire "-x yyyy"
		for(int i=0;i<args.length;i+=2){
			switch(args[i]){
			// Le temps d'attente avant d'estimer que la cible est injoignable
			case "-t":
				try {
					timeout = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
				}
				break;
			// Le nombre de temps affichés
			case "-n":
				try {
					NombresAffiches = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					// Pas de nombre dans l'argument : on ne fait rien
				}
				break;
			// Le décalage de l'affichage vers la droite
			case "-D":
				try {
					decalageDroite = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					// Pas de nombre dans l'argument : on ne fait rien
				}
				break;
			// Le décalage de l'affichage vers le haut
			case "-H":
				try {
					decalageHaut = Integer.parseInt(args[i+1]);
				} catch (NumberFormatException e){
					// Pas de nombre dans l'argument : on ne fait rien
				}
				break;
			}
			
		}
		
	}
}