package com.cmd.script.service;

import java.util.List;

/**
 * 进程服务接口
 */
public interface ProcessService {

    /**
     * 获取执行命令输出流
     * @param cmd
     * @return
     */
    List<String> getProcessContent(String cmd) throws Exception;

    /**
     * 获取Liunx进程ID
     * @param command
     * @return
     */
    String getPID(String command);

    /**
     * 获取Liunx进程ID
     * @param commands
     * @return
     */
    String getPID(String[] commands);

    /**
     * 获取Liunx进程ID
     * @param commands
     * @return
     */
    String getPID(String[] commands, String[] excludeCommands);

    /**
     * 关闭Liunx进程
     * @param commands 匹配字符集合
     * @param excludeCommands 排除字符集合
     */
    void closeProcess(String[] commands, String[] excludeCommands);

    /**
     * 关闭Liunx进程
     * @param pid
     */
    void closeProcess(String pid);
}
