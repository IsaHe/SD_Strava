package es.deusto.sd.strava.sd_strava.external;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class FacebookGateway implements IAuthPlatformGateway {

    private static final String FACEBOOK_SERVER_HOST = "localhost";
    private static final int FACEBOOK_SERVER_PORT = 8082;

    @Override
    public String authenticate(String email) {
        try (Socket socket = new Socket(FACEBOOK_SERVER_HOST, FACEBOOK_SERVER_PORT);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("AUTH:" + email);

            String response = in.readLine();
            if (response.startsWith("SUCCESS:")) {
                return response.substring(8).trim();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al comunicarse con el servidor de Facebook: " + e.getMessage());
            return null;
        }
    }

    public String addUser(String email, String name) {
        try (Socket socket = new Socket(FACEBOOK_SERVER_HOST, FACEBOOK_SERVER_PORT);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("ADD_USER:" + email + "," + name);

            String response = in.readLine();
            if (response.startsWith("SUCCESS:")) {
                return response.substring(8).trim();
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error al comunicarse con el servidor de Facebook: " + e.getMessage());
            return null;
        }
    }
}
