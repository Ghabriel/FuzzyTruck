package remoteDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
 
public class RemoteDriver {
	
	static int port = 4321;
	static String host = "localhost";
	
    public static void main(String[] args) throws IOException {
        	    	
        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            kkSocket = new Socket(host, port);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:"  + host);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + host);
            System.exit(1);
        }
 
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
 
        double x, y;
        double angle;
        
        // requisicao da posicao do caminhao
        out.println("r");
        while ((fromServer = in.readLine()) != null) {
        	StringTokenizer st = new StringTokenizer(fromServer);
        	x = Double.valueOf(st.nextToken()).doubleValue();
        	y = Double.valueOf(st.nextToken()).doubleValue();
        	angle = Double.valueOf(st.nextToken()).doubleValue();

        	System.out.println("x: " + x + " y: " + y + " angle: " + angle);
        	
        	/////////////////////////////////////////////////////////////////////////////////////
        	// TODO sua lógica fuzzy vai aqui use os valores de x,y e angle obtidos. x e y estao em [0,1] e angulo [0,360)
        	
        	
        	
			
//        	double teste = Double.valueOf(stdIn.readLine());
        	double d0 = Math.abs(angle);
        	double d90 = Math.abs(angle - 90);
        	double d180 = Math.abs(angle - 180);
        	double d270 = Math.abs(angle - 270);
        	double result = 0;
        	if (x <= 0.2) {
//        		if (d180 <= 15) {
        			result -= d180 * Math.signum(angle - 180);
//            	} else {
//            		result -= 30 * Math.signum(angle - 180);
//            	}
        	} else if (x <= 0.8) {
        		result -= d90 * Math.signum(angle - 90);
//        		double goal = 90;
//        		double delta = Math.abs(angle - goal);
//        		double sign = Math.signum(angle - goal);
        	} else {
        		result -= d0 * Math.signum(angle);
        	}

//        	if (y > 0.8) {
//        		double delta = 0;
//        		double sign = 0;
//        		if (x <= 0.3) {
//            		delta = Math.abs(angle - 225);
//            		sign = Math.signum(angle - 225);
//        		} else if (x >= 0.7) {
//            		delta = Math.abs(angle - 315);
//            		sign = Math.signum(angle - 315);
//        		}
//    			result -= delta * sign;
//        	}

        	
        	double respostaDaSuaLogica = result; // atribuir um valor entre -1 e 1 para virar o volante pra esquerda ou direita.
        	
        	
        	///////////////////////////////////////////////////////////////////////////////// Acaba sua modificacao aqui
        	// envio da acao do volante
        	out.println(respostaDaSuaLogica);
        	
            // requisicao da posicao do caminhao        	
        	out.println("r");	
        }
 
        out.close();
        in.close();
        stdIn.close();
        kkSocket.close();
    }
}