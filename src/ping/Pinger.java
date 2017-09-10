package ping;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe permettant de contacter un serveur et de calculer le temps de r�ponse
 * @author Mavel
 */

public class Pinger {
	
	// Cible � tester
	private byte[] adresse;
	
	// Temps maximal avant d'estimer que la cible est injoignable
	private int timeout;
	
	public Pinger(byte[] adresse, int timeout){
		this.adresse = adresse;
		this.timeout = timeout;
	}
	
	// Calcule et renvoie le temps de r�ponse du serveur
	public String ping(){
		// Temps de r�ponse en chaine ne caract�re
		String pingResult = "";
		// Bool�en permettant d'agir en fonction du succ�s ou de l'echec d'un ping
		boolean isPinged;
		// Temps de r�ponse
        long pingtime = 0;
        // Temps avant le test
        long startTime = 0;
        // Temps apr�s le test
        long endTime = 0;
    	
    	try {
        	// M�morisation du temps avant	    	
        	startTime = System.currentTimeMillis();
    		
    		// Essai de connexion
			isPinged = InetAddress.getByAddress(adresse).isReachable(timeout);
    	
			// M�morisation du temps apr�s
		    endTime = System.currentTimeMillis();
		    // On calcule la diff�rence de temps avant la connexion et apr�s (Entre les deux, un paquet IP a �t� envoy� (SYN) et une r�ponse a �r� re�ue (SYN/ACK))
		    pingtime = endTime - startTime;
		    
		    // H�te atteint ?
			if(isPinged){ // Oui
				// On transforme le temps en chaine de caract�res
				pingResult=Long.toString(pingtime);
			}else{ // Non
				// On signale une erreur au lieu d'un temps de ping si le contact ne s'est pas fait correctement
				pingResult="ERR!";
			}
    	} catch (UnknownHostException e) {
			// ERREUR H�te inconnu
    		pingResult = "UnknownHost";
		} catch (IOException e) {
			// ERREUR Autre erreur
			pingResult = "ERR!";
		}
		
		return pingResult;
	}
}
