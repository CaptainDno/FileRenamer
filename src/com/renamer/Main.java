package com.renamer;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Enter directory path:");
        Scanner s = new Scanner(System.in);
        File dir;
        while (true){
            String path = s.nextLine();
            dir = new File(path);
            if (dir.exists()) break;
            else System.out.println("Invalid directory path. Try again:");
        }
        System.out.println("""
                Now enter rename parameters by command replace [oldChars] [new]
                replaceStart [OldSymbol] [new] - replace all provided symbols from start of the fileName.
                All symbols will be replaced in order of commands, that you'll enter!
                Use *start* to start renaming""");
        ArrayList<ReplaceParam> params = new ArrayList<>();
        while (true){
            String command = s.nextLine();
            if (command.equals("start")) break;
            if (command.startsWith("replaceStart")){
                String[] strings = command.split(" ");
                String n = "";
                if (strings.length == 3) n = strings[2];
                params.add(new ReplaceParam(strings[1], n, true));
                continue;
            }
            if (command.startsWith("replace")){
                String[] strings = command.split(" ");
                String n = "";
                if (strings.length == 3) n = strings[2];
                params.add(new ReplaceParam(strings[1], n, false));
            }
        }
        String[] filenames = Objects.requireNonNull(dir.list());
        String newName = "";
        for (String name : filenames){
            newName = name;
            for (ReplaceParam p : params){
                if (p.isStart){
                    while (newName.startsWith(p.old)){
                        newName = newName.substring(p.old.length());
                    }
                }
                else newName = newName.replaceAll(p.old, p.newS);

            }
            new File(dir.getAbsolutePath() + System.getProperty("file.separator") + name).renameTo(new File(dir.getAbsolutePath() + System.getProperty("file.separator") + newName));
        }
        System.out.println("Success");
    }
}
