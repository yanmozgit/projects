package com.cmd.script.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 示例：执行进程并返回结果
 */
public class WindowsProcessServiceImpl {

    public static final int SUCCESS = 0; // 表示程序执行成功
    public static final String COMMAND = " netstat -ano|findstr 8082 "; // 要执行的语句
    public static final String SUCCESS_MESSAGE = "程序执行成功！";
    public static final String ERROR_MESSAGE = "程序执行出错：";

    /**
     * 程序入口
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // 执行程序
        Process process = Runtime.getRuntime().exec(COMMAND);
        // 打印程序输出
        List<List<String>> messages = readProcessOutput(process);
        for (int i = 0; i < messages.size(); i++) {
            List<String> list =  messages.get(i);
            for (int j = 0; j < list.size(); j++) {
                String s =  list.get(j);
                System.out.println(s);
            }
        }
        // 等待程序执行结束并输出状态
        int exitCode = process.waitFor();
        if (exitCode == SUCCESS) {
            System.out.println(SUCCESS_MESSAGE);
        } else {
            System.err.println(ERROR_MESSAGE + exitCode);
        }
    }

    /**
     * 读取进程进程
     *
     * @param process 进程
     */
    private static List<List<String>> readProcessOutput(final Process process) {
        List<List<String>> messages = new ArrayList<>();
        List<String> successLines = read(process.getInputStream());
        List<String> errorLines = read(process.getErrorStream());
        messages.add(successLines);
        messages.add(errorLines);
        return messages;
    }

    // 读取输入流
    private static List<String> read(InputStream inputStream) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lines;
    }
}