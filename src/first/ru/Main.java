package first.ru;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static String rootPath = ".";
    public static ArrayList<File> listFile = new ArrayList<>();

    public static void main(String[] args) {
        String rootPath = directory();

        File file = new File(rootPath);

        search(file);
        sort(listFile);
        createFile(rootPath + "/result.txt");
        System.out.println("the resulting file is in the folder: " + rootPath);
    }

    public static String directory(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search directory");
        String s = scanner.nextLine();
        rootPath = s;
        return rootPath;
    }

    public static void search(File dir) {
        if(dir.isDirectory()){
            for (File f: dir.listFiles()){
                if (f.isFile() && f.getName().endsWith(".txt")){
                    listFile.add(f);
                }else{
                    search(f);
                }

            }
        }else{
            System.out.println("The directory is entered incorrectly.");
            directory();
        }
    }

    public static void sort(ArrayList listFile){
        Comparator<File> comparator = new Comparator<File>(){
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        listFile.sort(comparator);
    }

    public static void createFile(String result){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(result));
            for (File file: listFile) {
                try(BufferedReader br = new BufferedReader(new FileReader(file.toString())))
                {
                    while (br.ready()){
                        bw.write(br.readLine());
                        bw.newLine();
                    }
                }
            }
            bw.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
