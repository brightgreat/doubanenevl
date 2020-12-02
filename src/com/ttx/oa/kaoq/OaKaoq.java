package com.ttx.oa.kaoq;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class OaKaoq {
    private static String SESSION_ID = "";


    public static void main(String[] args) throws Exception {
        //通过登录链接得到一个cookie
//        String loginUrl="http://oa.ittx.com.cn/api/hrm/login/checkLogin?loginid=hlwang@ittx.com.cn&userpassword=ec53c91144781b29d864d6a16a13e506_random_";
//        String dakaUrl="http://oa.ittx.com.cn/api/hrm/kq/attendanceButton/punchButton";
//        String sessionId=callHttp(loginUrl,"",true);
//        System.out.println("\r\n================================\r\n");
//        System.out.println(callHttp(dakaUrl, sessionId,false));
//        System.out.println(sessionId);
//        System.out.println("\r\n================================\r\n");
        System.out.println(readFileContent("./src/com/file/uAndp.txt"));

        String users=readFileContent("./src/com/file/uAndp.txt");
        if(null!=users){
            List<String> lusers= Arrays.asList(users);
            for (String susers:lusers){
                Map stringToMap =  JSONObject.parseObject(jsonString2);
            }

        }

    }

    //todo
    // 密文进行对称加密
    //账密信息读取配置文件，每次定时器跑到时，去做文件读取
    //邮件
    //计时器 或者使用cottrl
    //scheduler https://www.cnblogs.com/wang-yaz/p/8919411.html
    //输出本行内容及字符数 读取中文出现乱码

    static void readLineVarFile(String fileName, int lineNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); //使用缓冲区的方法将数据读入到缓冲区中
        String line = reader.readLine(); //定义行数
        if (lineNumber <= 0 || lineNumber > getTotalLines(fileName)) //确定输入的行数是否有内容
        {
            System.out.println("不在文件的行数范围之内。");
        }
        int num = 0;
        while (line != null) //当行数不为空时，输出该行内容及字符数
        {
            if (lineNumber == ++num) {
                System.out.println("第" + lineNumber + "行: " + line);
            }
            line = reader.readLine();
        }
        reader.close();
    }

    // 文件内容的总行数
    static int getTotalLines(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); ////使用缓冲区的方法将数据读入到缓冲区中
        LineNumberReader reader = new LineNumberReader(br);
        String s = reader.readLine(); //定义行数
        int lines = 0;
        while (s != null) //确定行数
        {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        br.close();
        return lines; //返回行数
    }

    public static String readFileContent(String fileName) {
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        StringBuffer sbf = new StringBuffer();
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempStr;
//            while ((tempStr = reader.readLine()) != null) {
//                sbf.append(tempStr);
//            }
//            reader.close();
//            return sbf.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }
//        return sbf.toString();

        try {
            File file = new File(fileName);
            if (file.isFile() && file.exists()) {
//读取的时指定GBK编码格式，若中文出现乱码请尝试utf-8，window默认编码格式为GBK
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                br.close();
                return lineTxt;
            } else {
                System.out.println("文件不存在!");
                return "文件不存在";
            }
        } catch (Exception e) {
            System.out.println("文件读取错误!");
            return e.toString();
        }

    }

    public static String callHttp(String callURL, String sessionId, boolean isLogin) throws Exception {
        String result = "";
//        String sessionId = "";
        URL u0 = new URL(callURL);
        HttpURLConnection conn = (HttpURLConnection) u0.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/plain");
        conn.setRequestProperty("Content-Language", "en-US");
        if (!isLogin) {
            conn.setRequestProperty("Cookie", sessionId);
        }
//        conn.setRequestProperty("Cookie","experimentation_subject_id=IjYwOGFkZjM5LTNlZDQtNGY5MS05MzAxLWI1MDdmNjc0ZjJmNiI%3D--01211de8bf1d54bde5c7c903f8490fcb79331245; languageidweaver=7; loginuuids=324; __randcode__=18e77b64-c48e-4320-b053-dd1eb283f33c; loginidweaver=hlwang%40ittx.com.cn; ecology_JSessionid=aaafL2O-cn4sH4rKC9Pjx; JSESSIONID=aaafL2O-cn4sH4rKC9Pjx; ecology_JSessionId=aaafL2O-cn4sH4rKC9Pjx");
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        // 捕获sessionId
        String key = null;
        for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
            if (key.equalsIgnoreCase("set-cookie")) {
                sessionId = conn.getHeaderField(key);
                sessionId = sessionId.substring(0, sessionId.indexOf(";"));
                break;
            }
        }

//        if (SESSION_ID != null && !"".equals(SESSION_ID)) {
//            // 已经记录sessionId则放入session中
//            conn.setRequestProperty("Cookie", SESSION_ID);
//        } else if ((SESSION_ID == null || "".equals(SESSION_ID)) && conn != null) {
//            // 捕获sessionId
//            String key = null;
//            for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) {
//                if (key.equalsIgnoreCase("set-cookie")) {
//                    SESSION_ID = conn.getHeaderField(key);
//                    SESSION_ID = SESSION_ID.substring(0, SESSION_ID.indexOf(";"));
//                    break;
//                }
//            }
//        }
        // 自动捕获网页编码，并按其编码方式读取网页内容
        String charset = getChareset(conn.getContentType());
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        result = buffer.toString();

        conn.disconnect();
        System.out.println(result);
//        return result;
        //如果登录成功，这里就把session返回出去
        if (isLogin && result.toLowerCase().contains("成功")) {
            return sessionId;
        } else {
            return result;
        }

    }

    /**
     * 获取网页编码方式
     *
     * @param contentType
     * @return
     */
    public static String getChareset(String contentType) {
        int i = contentType == null ? -1 : contentType.indexOf("charset=");
        return i == -1 ? "UTF-8" : contentType.substring(i + 8);
    }

}
