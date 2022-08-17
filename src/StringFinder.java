import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class StringFinder {

    public static void main(String[] args) throws IOException {
        System.out.println("Removing " + args[0] + " from all configs");
        System.out.println("Getting all configs in path " + args[1]);
        File folder = new File(args[1]);
        for (File currentfile: Objects.requireNonNull(folder.listFiles())){  //loop through files
            System.out.println("Processing file " + currentfile.getName());
            FileInputStream fileInputStream = new FileInputStream(currentfile);
            Scanner scan = new Scanner(fileInputStream);
            StringBuilder sb = new StringBuilder();
            Boolean b = false;
            while(scan.hasNextLine()){
                String line = scan.nextLine(); //read in current line
                if(line.contains(args[0])){ //if line contains string to remove
                    b = false;
                }
                else if (line.contains("biomesoplenty") || line.contains("minecraft"))
                    b = true;

                if (b){
                    sb.append(line); //append line to string builder only if there is things
                    sb.append("\n"); //append new line
                }

            }
            String filename = currentfile.getName();
            currentfile.delete();
            File newfile = new File(filename);
            newfile.createNewFile();
            FileWriter fileWriter = new FileWriter(newfile);
            fileWriter.write(sb.toString());
        }
        System.out.println("Done");
   }

}
