import java.awt.Color;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class main {
	public static void main(String[] args){
	    Fenetre fenetre = new Fenetre();
	    fenetre.setBackground(new Color(0,0,0,0));
	    List<String> pings = new ArrayList<String>();
	    int NmbresAffiches=30;
	    for (int i=0;i!=NmbresAffiches;i++){
	    	pings.add("0");
	    }
	    fenetre.affiche(NmbresAffiches, pings);
	    for(;;){
	        boolean isPinged;
	        long pingtime = 0;
		    try (Socket soc = new Socket()) {
		    	long startTime = System.currentTimeMillis();
		        soc.connect(new InetSocketAddress("8.8.8.8", 53), 10000);
		        long endTime = System.currentTimeMillis();
		        pingtime = endTime - startTime;
		        soc.close();
		        isPinged = true;
		    } catch (IOException ex) {
		        isPinged = false;
		    }
			try {
		    	Thread.sleep(500);
		    } catch (InterruptedException e) {
		    	e.printStackTrace();
		    }
			String pt=Long.toString(pingtime);
			if(!isPinged){
				pt="Nope";
			}
    		pings.remove(NmbresAffiches-1);
    		pings.add(pt);
        	for (int i=NmbresAffiches-1;i!=0;i--){
        		String j=pings.get(0);
        		pings.remove(0);
        		pings.add(j);
        		fenetre.affiche(NmbresAffiches, pings);
	        }
	    }
	}
}