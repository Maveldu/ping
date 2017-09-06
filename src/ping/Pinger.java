package ping;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Classe permettant de contacter un serveur et de calculer le temps de réponse
 * @author Mavel
 */

public class Pinger {
	
	// Cible à tester
	private String adresse;
	
	// Port de la cible utilisé
	private int port;
	
	// Temps maximal avant d'estimer que la cible est injoignable
	private int timeout;
	
	public Pinger(String adresse, int port, int timeout){
		this.adresse = adresse;
		this.port = port;
		this.timeout = timeout;
	}
	
	// Calcule et renvoie le temps de réponse du serveur
	public String ping(){
		// Temps de réponse en chaine ne caractère
		String pingResult;
		// Booléen permettant d'agir en fonction du succès ou de l'echec d'un ping
		boolean isPinged;
		// Temps de réponse
        long pingtime = 0;
        // Temps avant le test
        long startTime = 0;
        // Temps après le test
        long endTime = 0;
        
	    try (Socket soc = new Socket()) {
	    	// Mémorisation du temps avant
	    	startTime = System.currentTimeMillis();
	    	// Essai de connexion
	        soc.connect(new InetSocketAddress(adresse, port), timeout);
	        // Mémorisation du temps après
	        endTime = System.currentTimeMillis();
	        // On calcule la différence de temps avant la connexion et après (Entre les deux, un paquet IP a été envoyé (SYN) et une réponse a éré reçue (SYN/ACK))
	        pingtime = endTime - startTime;
	        // Fermeture de la connexion au serveur
	        soc.close();
	        // Succès du contact
	        isPinged = true;
	    } catch (IOException ex) {
	    	// Une erreur est survenue lors du test (hote injoigable en général)
	        isPinged = false;
	    }
	    // On transforme le temps en chaine de caractères
		pingResult=Long.toString(pingtime);
		// On signale une erreur au lieu d'un temps de ping si le contact ne s'est pas fait correctement
		if(!isPinged){
			pingResult="ERR!";
		}
		
		return pingResult;
	}
}
