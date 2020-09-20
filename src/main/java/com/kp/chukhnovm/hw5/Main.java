package com.kp.chukhnovm.hw5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        copyFiles("files_from","files_to");
        copyFileContent("files_to/task2_file1.txt", "files_from/task2_file1.txt", "files_from/task2_file2.docx");
    }

    /**
     * Copy files with allowed extensions
     */
    public static void copyFiles(String sourceFolder, String targetFolder){

        try {
            FileFilter fF = new FileFilter("doc", "pdf");
            File folder = new File(sourceFolder);
            File[] fileList = folder.listFiles(fF);
            for (File file : fileList) {
                Files.copy(
                        Paths.get(file.getPath()),
                        Paths.get(targetFolder + "/" + file.getName()),
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy files with allowed extensions
     */
    public static void copyFileContent(String toFile, String...fromFiles){

        File file = new File(toFile);

        boolean append = false;
        for (String filePath: fromFiles) {
            try (FileInputStream fis = new FileInputStream(filePath);
                 FileOutputStream fos = new FileOutputStream(toFile, append)) {
                System.out.println(String.format("===Write from:%s to:", filePath, toFile));

                byte[] bufer = new byte[1024];
                int chunk = 0;
                int byteread = 0;
                while ((byteread = fis.read(bufer)) > 0) {
                    System.out.println(String.format("Read chunk №%s | read %s bytes", ++chunk, byteread));
                    fos.write(bufer, 0, byteread);
                }

                System.out.println(String.format("File size:", file.length()));

                append = true;
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
