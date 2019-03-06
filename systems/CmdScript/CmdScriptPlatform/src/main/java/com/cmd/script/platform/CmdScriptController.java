package com.cmd.script.platform;

import com.cmd.script.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 进程工具类
 */
@Controller
@RequestMapping("/")
public class CmdScriptController {

    /**
     * 日志对象
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "linuxProcessService")
    private ProcessService linuxProcessService;


    //测试接口
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(HttpServletRequest request) throws Exception {
        return "SUCCESS!!!";
    }


    /**
     * 获取进程ID
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLinuxPID", method = RequestMethod.GET)
    @ResponseBody
    public String getLinuxPID(HttpServletRequest request) throws Exception {
        String pid = " 当前参数未找到相关进程！ ";
        try {
            String command = request.getParameter("command");
            pid = linuxProcessService.getPID(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pid;
    }

    /**
     * 获取进程ID
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLinuxPID2", method = RequestMethod.GET)
    @ResponseBody
    public String getLinuxPID2(HttpServletRequest request) throws Exception {
        String pid = " 当前参数未找到相关进程！ ";
        try {
            String strCommand = request.getParameter("commands");
            String[] commands = strCommand.split("\\|");
            pid = linuxProcessService.getPID(commands);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pid;
    }

    /**
     * 获取进程ID
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getLinuxPID3", method = RequestMethod.GET)
    @ResponseBody
    public String getLinuxPID3(HttpServletRequest request) throws Exception {
        String pid = " 当前参数未找到相关进程！ ";
        try {
            String strCommand = request.getParameter("commands");
            String strExcludeCommand = request.getParameter("excludeCommands");
            String[] commands = strCommand.split("\\|");
            String[] excludeCommands = strExcludeCommand.split("\\|");
            pid = linuxProcessService.getPID(commands, excludeCommands);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pid;
    }

    /**
     * 关闭Liunx进程ID
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/closeLinuxProcess", method = RequestMethod.GET)
    @ResponseBody
    public String closeLinuxProcess(HttpServletRequest request) throws Exception {
        String result = "success|成功关闭";
        try {
            String pid = request.getParameter("pid");
            linuxProcessService.closeProcess(pid);
        } catch (Exception e) {
            e.printStackTrace();
            result = "fail|关闭失败：" + e.getMessage();
        }
        return result;
    }

    /**
     * 关闭Liunx进程ID
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/closeLinuxProcess3", method = RequestMethod.GET)
    @ResponseBody
    public String closeLinuxProcess3(HttpServletRequest request) throws Exception {
        String result = "success|成功关闭";
        try {
            String strCommand = request.getParameter("commands");
            String strExcludeCommand = request.getParameter("excludeCommands");
            String[] commands = strCommand.split("\\|");
            String[] excludeCommands = strExcludeCommand.split("\\|");
            linuxProcessService.closeProcess(commands, excludeCommands);
        } catch (Exception e) {
            e.printStackTrace();
            result = "fail|关闭失败：" + e.getMessage();
        }
        return result;
    }








}
