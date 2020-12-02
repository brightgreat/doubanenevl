package com.pags.get.date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网页源码解析工具
 */
public class GetHtmlBuyUrlA {

    /**
     * 通过网页获取源代码
     *
     * @param urlObj 网址
     * @param encord 网页编码
     * @return 源码
     */
    public static String getHtml(String urlObj, String encord) {
        BufferedReader bfr = null;
        InputStream isr = null;
        StringBuffer sb = new StringBuffer();
        try {
            //建立链接
            URL url = new URL(urlObj);
            //打开链接
            URLConnection uc = url.openConnection();
            uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            //创建文件流
            isr = uc.getInputStream();
            //开始读取
//            isr.read()
            bfr = new BufferedReader(new InputStreamReader(isr, encord));
//            bfr.readLine();
            //通过循环来读取
            String line = "";
            while ((line = bfr.readLine()) != null) {
                sb.append(line);
//                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {

                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取评价内容
     *
     * @param page 页码参数
     * @return
     */
    public static List<String> getEnvlue(int page) {
        int start = 0;
        String encord = "UTF-8";
        List<String> envlueContons = new ArrayList<>();
        for (int i = 0; i <= page; i++) {
            String urlObj = "https://movie.douban.com/subject/26794435/comments?start=" + start + "&limit=20&sort=new_score&status=P";
            //获取源代码
            String html = getHtml(urlObj, encord);
            //解析源代码
//        System.out.println(html);
//
            Document document = Jsoup.parse(html);
            Element review = document.getElementById("comments");
            //获取用户评价列表集合
            Elements list = review.getElementsByClass("comment-item");
            int size = list.size();

            for (int j = 0; j < size; j++) {
                //获取评价内容
                String eval = list.get(j).getElementsByClass("short").text();
                envlueContons.add(eval);
//                System.out.println(eval);
//                System.out.println("=====================");
            }

            start += 20;
        }
        System.out.println(start);

        return envlueContons;
    }

    //主函数
    public static void main(String[] args) throws Exception {
//        System.out.println("亲爱的宝贝");
        List<String> envlue = getEnvlue(10);
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\Administrator\\Desktop\\evlvie\\123.txt"), false);

        for (String content : envlue) {
            //去除非中文字符
//            String rex="[^\\u4e00-\\u9fa5]";
//            Pattern compile = Pattern.compile(rex);
//
//            Matcher matcher = compile.matcher(content);
//            content=matcher.replaceAll("");
//            content=content+"\n\r";
            System.out.println(content);
            fos.write(content.getBytes());
            fos.write("\r\n".getBytes());
        }
        fos.close();

    }
}
