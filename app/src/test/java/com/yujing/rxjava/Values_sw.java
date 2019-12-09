package com.yujing.rxjava;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Values_sw {
    private static DecimalFormat df = new DecimalFormat("#0.00");
    @Test
    public void run() {
        //        //屏幕最小宽度
        //        Configuration config = getResources().getConfiguration();
        //        int smallestScreenWidth = config.smallestScreenWidthDp;
        setDimen(1d, "values");
        setDimen(1d, "values-sw720dp");
        setDimen(300d / 720d, "values-sw300dp");
        setDimen(320d / 720d, "values-sw320dp");
        setDimen(340d / 720d, "values-sw360dp");
        setDimen(392d / 720d, "values-sw392dp");
        setDimen(392d / 720d, "values-sw392dp");
        setDimen(411d / 720d, "values-sw411dp");
        setDimen(480d / 720d, "values-sw480dp");
        setDimen(540d / 720d, "values-sw540dp");
        setDimen(640d / 720d, "values-sw640dp");
    }

    static void setDimen(double scale, String name) {
        StringBuilder sb = new StringBuilder();
        PrintWriter out;
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + "<resources>");
            //dp
            for (int i = 0; i <= 500; i++) {
                sb.append("<dimen name=\"dp" + i + "\">" + df.format(i * scale) + "dp</dimen>\r\n");
            }
            //sp
            for (int i = 0; i <= 500; i++) {
                sb.append("<dimen name=\"sp" + i + "\">" + df.format(i * scale) + "sp</dimen>\r\n");
            }
            sb.append("</resources>");

            //这里是文件名，1 注意修改 sw 后面的值，和转换值一一对应  2 文件夹和文件要先创建好否则要代码创建
            String fileDef = "src/main/res/" + name + "/dimens.xml";
            File file = new File(fileDef);
            file.getParentFile().mkdirs();
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            out.println(sb.toString());
            out.close();
            System.out.println("创建完成：" + name + " 比例为：" + df.format(scale)+" 路径为："+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
