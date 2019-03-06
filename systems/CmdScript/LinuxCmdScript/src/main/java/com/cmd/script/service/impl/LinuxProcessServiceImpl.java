package com.cmd.script.service.impl;

import com.cmd.script.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 进程服务类
 */
@Service("linuxProcessService")
public class LinuxProcessServiceImpl implements ProcessService {

    //日志记录
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<String> getProcessContent(String cmd) throws Exception{
        List<String> lines = new ArrayList<>();
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    @Override
    public String getPID(String command) {
        logger.info("==ymz 获取收割查询的PID关键字信息： " + command);
        String cmd = "ps -ef";
        String result = "";
        try {
            List<String> lines = getProcessContent(cmd);
            for (int i = 0; i < lines.size(); i++) {
                String line =  lines.get(i);
                if (line.contains(command)) {
                    logger.info("当前进程信息 -----> " + line);
                    String[] strs = line.split("\\s+");
                    result = strs[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    @Override
    public String getPID(String[] commands) {
        logger.info("==ymz 获取收割查询的PID关键字信息： " + Arrays.toString(commands));
        String cmd = "ps -ef";
        String result = "";
        try {
            List<String> lines = getProcessContent(cmd);
            for (int i = 0; i < lines.size(); i++) {
                String line =  lines.get(i);
                boolean flag = checkAllContains(commands, line);
                if (flag) {
                    logger.info("当前进程信息 -----> " + line);
                    String[] strs = line.split("\\s+");
                    result = strs[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }



    @Override
    public String getPID(String[] commands, String[] excludeCommands) {
        logger.info("==ymz 获取收割查询的PID关键字信息： " +
                "| 匹配字符串：" + Arrays.toString(commands) +
                "| 排除字符串：" + Arrays.toString(excludeCommands));
        String cmd = "ps -ef";
        String result = "";
        try {
            List<String> lines = getProcessContent(cmd);
            for (int i = 0; i < lines.size(); i++) {
                String line =  lines.get(i);
                boolean containsFlag = checkAllContains(commands, line);
                boolean excludeFlag = checkAllNotContains(excludeCommands, line);
                if (containsFlag && excludeFlag) {
                    logger.info("当前进程信息 -----> " + line);
                    String[] strs = line.split("\\s+");
                    result = strs[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }


    @Override
    public void closeProcess(String[] commands, String[] excludeCommands) {
        String pid = getPID(commands, excludeCommands);
        logger.info("==ymz 查询出当前进程ID：" + pid);
        if(pid != null){
            closeProcess(pid);
        }
    }

    @Override
    public void closeProcess(String pid) {
        Process process = null;
        BufferedReader reader = null;
        try {
            //杀掉进程
            process = Runtime.getRuntime().exec("kill -9 " + pid);
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                logger.info("kill PID return info -----> " + line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 检查当前字符串是否包含所有字符串匹配集合
     * @param contents
     * @param content
     * @return
     */
    private boolean checkAllContains(String[] contents, String content){
        boolean flag = false;
        for (int j = 0; j < contents.length; j++) {
            String command =  contents[j];
            if(content.contains(command)){
                flag = true;
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }



    /**
     * 检查当前字符串是否不包含所有字符串匹配集合
     * @param contents
     * @param content
     * @return
     */
    private boolean checkAllNotContains(String[] contents, String content){
        boolean flag = true;
        for (int j = 0; j < contents.length; j++) {
            String command =  contents[j];
            if(!content.contains(command)){
                flag = true;
            }else{
                flag = false;
                break;
            }
        }
        return flag;
    }
}
