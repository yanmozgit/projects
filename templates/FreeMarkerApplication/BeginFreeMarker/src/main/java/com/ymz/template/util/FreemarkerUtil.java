package com.ymz.template.util;

import java.io.*;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {

    /**
     * 获取模板信息
     * @param name
     * @return
     */
    public static Template getTemplate(String name) {
        try {
            // 通过Freemarker的Configuration读取相应的ftl
            // 这里是对应的你使用jar包的版本号：<version>2.3.23</version>
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);

            //configuration.setDirectoryForTemplateLoading(new File("/ftl")); //如果是maven项目可以使用这种方式
            //第二个参数 为你对应存放.ftl文件的包名
            configuration.setClassForTemplateLoading(Template.class, "/ftl");

            Template template = configuration.getTemplate(name);

            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 控制台输出
     * @param name
     * @param root
     */
    public static void print(String name, Map<String, Object> root) {
        //通过Template可以将模版文件输出到相应的文件流
        Template template = getTemplate(name);
        try {
            template.process(root, new PrintWriter(System.out));//在控制台输出内容
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 输出到制定输入流
     * @param name
     * @param root
     */
    public static void writePrint(String name, Map<String, Object> root, Writer writer) {
        //通过Template可以将模版文件输出到相应的文件流
        Template template = getTemplate(name);
        try {
            template.process(root, writer);//在控制台输出内容
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 输出到文件
     * @param name 模板文件名
     * @param root 数据
     * @param fullFileName 输出完整文件名
     */
    public static void filePrint(String name, Map<String, Object> root, String fullFileName) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File(fullFileName));
            Template temp = getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
