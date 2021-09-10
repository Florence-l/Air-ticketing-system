package com.example.demo2.controller;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;


@Controller
public class DataAnalysisController{
    private static final String host = "47.115.202.133";
    private static final String username = "root";
    private static final String password="Air123456";
    private static final Connection conn = new Connection(host);
    private static final Logger LOGGER = LoggerFactory.getLogger(DataAnalysisController.class);
    public static double m;
    public static double d;
    public static double t;
    public static double c;
    public static double s;
    @GetMapping("/dataAnalysis")
    public String analysis(){
        return "dataAnalysis";
    }

    @RequestMapping("/predict")
    @ResponseBody()
    public String predict(){
        exec("python3 run.py runserver");
        exec("ls /root");
        getFile("result.xlsx", "D://");
        System.out.println("1");
        return "下载成功";
    }

    @RequestMapping("/p")
    @ResponseBody()
    public String p(HttpServletRequest request) throws Exception {
        m = Double.parseDouble(request.getParameter("m"));
        t = Double.parseDouble(request.getParameter("t"));
        c = Double.parseDouble(request.getParameter("c"));
        s = Double.parseDouble(request.getParameter("s"));
        d = Double.parseDouble(request.getParameter("d"));
        m=(m-1488)/845;
        t=(t-172)/182;
        c=(c-12)/14;
        s=(s-17322)/21053;
        d=(d-0.722)/0.184;

        System.out.println(m+t+c+s+d);
        FileInputStream fs=new FileInputStream("D://poi_test.xls");  //获取d://test.xls
        POIFSFileSystem ps=new POIFSFileSystem(fs);  //使用POI提供的方法得到excel的信息
        HSSFWorkbook wb=new HSSFWorkbook(ps);
        HSSFSheet sheet=wb.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
        HSSFRow row=sheet.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
        System.out.println(sheet.getLastRowNum()+" "+row.getLastCellNum());  //分别得到最后一行的行号，和一条记录的最后一个单元格

        FileOutputStream out=new FileOutputStream("D://poi_test.xls");  //向d://test.xls中写数据
        row=sheet.createRow((short)(1)); //在现有行号后追加数据

//填充列数据，这里是从左往右，依次填充数据，可以根据自己需求来填充，方法setCellValue()里面的参数是我自己传进来的，数据类型可以查看源码
        row.createCell(0).setCellValue(m);
        row.createCell(1).setCellValue(t);
        row.createCell(2).setCellValue(c);
        row.createCell(3).setCellValue(s);
        row.createCell(4).setCellValue(d);
        row.createCell(5).setCellValue('A');

        out.flush();
        wb.write(out);
        out.close();
        System.out.println(row.getPhysicalNumberOfCells()+" "+row.getLastCellNum());
        putFile("D://poi_test.xls", "/root");
        exec("python run2.py");
        getFile("result2.xlsx", "D://");

        FileInputStream fs1= new FileInputStream(new File("D://result2.xlsx"));
        //POIFSFileSystem ps1=new POIFSFileSystem(fs1);  //使用POI提供的方法得到excel的信息
        XSSFWorkbook wb1=new XSSFWorkbook(fs1);
        XSSFSheet sheet1=wb1.getSheetAt(0);  //获取到工作表，因为一个excel可能有多个工作表
        XSSFRow row1=sheet1.getRow(0);  //获取第一行（excel中的行默认从0开始，所以这就是为什么，一个excel必须有字段列头），即，字段列头，便于赋值
        System.out.println(row1.getCell(0));
        String a= String.valueOf(row1.getCell(0));
        System.out.println(a);
        return a;

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
            LOGGER.info("上传文件");
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
    public static void getFile(String localFile,String remoteTargetDirectory){
        try {
            conn.connect();
            if(isAuthed()){
                SCPClient scpClient = conn.createSCPClient();
                scpClient.get(localFile, remoteTargetDirectory);
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
//    public static void out(){
//        try {
//            conn.connect();
//            if(isAuthed()){
//                Session session = conn.openSession();
//                OutputStream output = session.getStdin();
//                byte[] data = new byte[1024];
//                output.write();
//                String result = new String(data,0,data.length);
//                System.out.println(result);
//            }else{
//                System.out.println("authentication fail.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            conn.close();
//        }
//    }


}
