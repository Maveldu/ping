package ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe permettant de contacter un serveur et de calculer le temps de réponse
 * @author Mavel
 */

public class Pinger {
	
	// Cible à tester
	private byte[] adresse;
	
	// Temps maximal avant d'estimer que la cible est injoignable
	private int timeout;
	
	public Pinger(byte[] adresse, int timeout){
		this.adresse = adresse;
		this.timeout = timeout;
	}
	
	// Calcule et renvoie le temps de réponse du serveur
	public String ping(){
		// Temps de réponse en chaine ne caractère
		String pingResult = "";
		// Booléen permettant d'agir en fonction du succès ou de l'echec d'un ping
		boolean isPinged;
		// Temps de réponse
        long pingtime = 0;
        // Temps avant le test
        long startTime = 0;
        // Temps après le test
        long endTime = 0;
    	
    	try {
        	// Mémorisation du temps avant	    	
        	startTime = System.currentTimeMillis();
    		
    		// Essai de connexion
			isPinged = InetAddress.getByAddress(adresse).isReachable(timeout);
    	
			// Mémorisation du temps après
		    endTime = System.currentTimeMillis();
		    // On calcule la différence de temps avant la connexion et après (Entre les deux, un paquet IP a été envoyé (SYN) et une réponse a éré reçue (SYN/ACK))
		    pingtime = endTime - startTime;
		    
		    // Hôte atteint ?
			if(isPinged){ // Oui
				// On transforme le temps en chaine de caractères
				pingResult=Long.toString(pingtime);
			}else{ // Non
				// On signale une erreur au lieu d'un temps de ping si le contact ne s'est pas fait correctement
				pingResult="ERR!";
			}
    	} catch (UnknownHostException e) {
			// ERREUR Hôte inconnu
    		pingResult = "UnknownHost";
		} catch (IOException e) {
			// ERREUR Autre erreur
			pingResult = "ERR!";
		}
		
		return pingResult;
	}
}
