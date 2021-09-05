package com.example.demo2.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;

@Controller
public class DataAnalysisController{
    private static final String host = "47.115.202.133";
    private static final String username = "root";
    private static final String password="Air123456";
    private static final Connection conn = new Connection(host);
    private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalysisController.class);

    @GetMapping("/dataAnalysis")
    public String analysis(){
        return "dataAnalysis";
    }

    @RequestMapping("/upload")
    @ResponseBody()
    public String upload(@RequestParam("file") MultipartFile file)throws IOException {
        if (file.isEmpty()) {
            return "error";
        }
        File dir = new File("uploadFile");
        System.out.println(dir.getAbsolutePath());
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String filePath=dir.getAbsolutePath()+"\\";

        File dest = new File(filePath+fileName);
        try {
            file.transferTo(dest);
            LOGGER.info("上传成功");
            putFile(filePath+ fileName, "/root");
            exec("ls /root");
            return "success";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "fail";
    }

    private static boolean isAuthed(){
                try {
                    return conn.authenticateWithPassword(username, password);
                } catch (Exception e) {
                    e.printStackTrace();
        }
        return false;
    }

    public static void putFile(String localFile,String remoteTargetDirectory){
        try {
            conn.connect();
            if(isAuthed()){
                SCPClient scpClient = conn.createSCPClient();
                scpClient.put(localFile, remoteTargetDirectory);
                System.out.println("upload file successfully.");
            }else{
                System.out.println("authentication fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }

    public static void exec(String command){
        try {
            conn.connect();
            if(isAuthed()){
                Session session = conn.openSession();
                session.execCommand(command);
                InputStream input = session.getStdout();
                byte[] data = new byte[1024];
                input.read(data);
                String result = new String(data,0,data.length);
                System.out.println(result);
            }else{
                System.out.println("authentication fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            conn.close();
        }
    }
}
