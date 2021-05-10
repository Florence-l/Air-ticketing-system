package com.example.demo2.Util;

public class RequiredUtil {

    /**
     * 判断request中的getParameter是否有值
     * 参数：int
     * 返回值：Boolean
     * */
    public Boolean Required(int i) {
        Boolean flage = false;
        if(i>0) {
            flage = true;
        }
        return flage;
    }

    /**
     * 判断request中的getParameter是否有值
     * 参数：String
     * 返回值：Boolean true:有值    false：无值
     * */
    public Boolean Required(String i) {
        Boolean flage = false;
        if(i.length()>0||i!=null||i!="") {
            System.out.println("Required:【"+i+"】");
            flage = true;
        }
        System.out.println("Required:"+flage);
        return flage;
    }


}
