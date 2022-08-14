package com.ftpserver;

import java.io.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;

public class Ftp {

    static String localPath = "F:/FTPLocal/";

    static String serverName = "localhost";
    static int port = 2121;
    static String username = "admin";
    static String password = "12345";

    static boolean isOpen = true;

    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args)  {
        int choice;
        FTPClient ftpClient = null;

        // creates local file
        File localFile = new File(localPath + "students.json");

        try {
            //server starts
            FtpServer server = Server.start();
            //loop for logging
            while (ftpClient == null) {
                //greetings();
                // creates client
                ftpClient = createClient();
            }
            download(ftpClient, localFile.getName());
            // basic loop of the app
            while (isOpen) {
                JSONWorker.read();

                System.out.println("\nPlease input a command:\n" +
                        "1 - Get students list\n" +
                        "2 - Find student by id\n" +
                        "3 - Add new student\n" +
                        "4 - Delete student by id\n" +
                        "5 - Close program");

                choice = scn.nextInt();

                switch (choice) {
                    case (1):
                        JSONWorker.list();
                        break;
                    case(2):
                        JSONWorker.search();
                        break;
                    case(3):
                        JSONWorker.createStudent();
                        break;
                    case(4):
                        JSONWorker.delete();
                        break;
                    case(5):
                        upload(ftpClient, localFile);
                        isOpen = false;
                        Server.stop(server);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FtpException e) {
            throw new RuntimeException(e);
        }
    }

    public static FTPClient createClient() throws IOException {
        // creates client for FTP Server
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(serverName, port);
        boolean done = ftpClient.login(username, password);
        if (done) {
            System.out.println("\nYou are connected and logged into " + serverName + ":" + port);
        } else {
            System.out.println("\nUsername or password is incorrect for " + serverName + ":" + port +
                    "\nRebooting into program...");
            return null;
        }
        ftpClient.enterLocalPassiveMode();
        return ftpClient;
    }

    public static void  greetings() {
        System.out.println("\nHello and welcome to FTPServer App for students!");
        System.out.println("Please input server address:");
        serverName = scn.nextLine();
        System.out.println("Please input server port:");
        port = Integer.parseInt(scn.nextLine());
        System.out.println("Please input login:");
        username = scn.nextLine();
        System.out.println("Please input password:");
        password = scn.nextLine();

    }

    public static void upload(FTPClient ftpClient, File localFile) throws IOException {
        // uploads local file to FTPServer using an InputStream

        InputStream inputStream = new FileInputStream(localFile);

        System.out.println("Start uploading " + localFile.getName() + " file");
        boolean done = ftpClient.storeFile(localFile.getName(), inputStream);
        inputStream.close();
        if (done) {
            System.out.println(localFile.getName() + " is uploaded successfully.");
        } else {
            System.out.println(localFile.getName() + " is not uploaded");
        }
    }

    public static void download(FTPClient ftpClient, String targetFile) throws IOException {
        //downloads file from FTPServer using FileOutputStream

        File localFile = new File("F:/FTPLocal/" + targetFile);
        FileOutputStream outputStream = new FileOutputStream(localFile);

        System.out.println("Start downloading " + targetFile + " file");
        boolean done = ftpClient.retrieveFile("/" + targetFile, outputStream);
        outputStream.close();
        if (done) {
            System.out.println(targetFile + " is downloaded successfully.");
        } else {
            System.out.println(targetFile + " is not downloaded");
        }
    }
}
