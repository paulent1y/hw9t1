package by.pavlunt1y;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Util {

    public static String generatePassword() {
        Random r = new Random();
        String res =  Integer.toString(r.nextInt(Integer.MAX_VALUE), 36).toUpperCase()
                + "_9a"
                + Integer.toString(r.nextInt(Integer.MAX_VALUE), 36);
        return res;
    }

    public static String generateEmail() {
        Random r = new Random();
        String randomPart = Integer.toString(r.nextInt(Integer.MAX_VALUE), 36) + "." + Integer.toString(r.nextInt(Integer.MAX_VALUE), 36);
        return "ml" + randomPart + "@gmail.com";
    }

    public static void saveCredsToFile(String mail, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("creds.txt", true))){
            bw.append(mail).append(",").append(password).append("\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getCredsFromFile() {
        return getCredsFromFile("creds.txt");
    }

    public static String getCredsFromFile(String filename) {
        List<String> all = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                all.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return all.get(new Random().nextInt(all.size()));
    }


}
