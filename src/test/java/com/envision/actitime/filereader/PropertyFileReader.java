package com.envision.actitime.filereader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class PropertyFileReader {
    private static Map<Object,Object> allTestPropertyData= new HashMap<>();
    static {
        readProperties();
    }

    private static void readProperties() {
        File file_or_folder= new File("actitime_testdata");
        if(file_or_folder.isDirectory()){
            List<File> allFilesandFolder = Arrays.asList(file_or_folder.listFiles());
            for (File each:allFilesandFolder){
                if(each.getName().endsWith(".properties")){
                    try {
                        FileInputStream fis=new FileInputStream(each);
                        Properties p =new Properties();
                        p.load(fis);
                      //  System.out.println(p);
                        allTestPropertyData.putAll(p);
                    } catch (IOException e) { //FileNotFoundException > IOException
                        throw new RuntimeException(e);
                    }

                }
            }
        }

    }
    public static String getPropertyValue(String propertyName){
        return allTestPropertyData.get(propertyName).toString();
    }

//    public static void main(String[] args) {
//        System.out.println(allTestPropertyData);
//        System.out.println(getPropertyValue("actitime.runmode"));
//    }

}
