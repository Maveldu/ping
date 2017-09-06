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
 * Cette application calcule le temps de r�ponse d'un serveur
 * Nous appelerons ici cette pratique un "ping" bien que nous n'utilisons pas le protocole ICMP
 * @author Mavel
 */

public class Controleur {
	
	// ----- Param�tres de l'application -----
	private static String adresse = "8.8.8.8";
	private static int port = 53;
	private static int timeout = 10000;
	private static int NombresAffiches = 30;
	// ---------------------------------------
	
	
	public static void main(String[] args){
		
		// Ouverture de la fenetre
	    Fenetre fenetre = new Fenetre();
	    // Cr�ation de l'objet permettant de faire nos tests
	    Pinger pinger = new Pinger(adresse, port, timeout);
	    // Initialisation de notre liste de temps de ping
	    List<String> pings = new ArrayList<String>();
	    // Initialisation de notre variable permettant de r�cup�rer nos temps
	    String pingResult;
	    
	    // Arri�re plan de la fenetre transparent
	    fenetre.setBackground(new Color(0,0,0,0));
	    
	    // Initialement nos temps seront � 0
	    for (int i=0;i!=NombresAffiches;i++){
	    	pings.add("0");
	    }
	    fenetre.affiche(NombresAffiches, pings);
	    
	    // Boucle infinie principale de l'application
	    while(true){
	    	// On calcule le temps de ping
	    	pingResult = pinger.ping();
	       
	    	// On ajoute ce r�sultat � notre liste
    		pings.remove(NombresAffiches-1);
    		pings.add(pingResult);
    		
    		// On d�file les �l�ments de la file afin que notre nouvel �l�ment soit positionn� � la premi�re place
    		String j="0";
           	for (int i=0;i<NombresAffiches-1;i++){
    			j=pings.get(0);
    			pings.remove(0);
    			pings.add(j);
    		}
           	
           	// On actualise notre affichage avec le nouveau r�sultat
        	fenetre.affiche(NombresAffiches, pings);
        	
        	// Petit temps afin d'�viter de faire tourner l'application trop vite et de faire des pings trop fr�quents
    		try {
    	    	Thread.sleep(500);
    	    } catch (InterruptedException e) {
    	    	e.printStackTrace();
    	    }
	    }
	}
}