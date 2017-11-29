package cn.erp.keywords.util;
import org.apache.log4j.Logger;


/**
 * Created by mike.liu on 2017/7/31.
 */

public class Log {
    //初始化log4j
    private static Logger Log = Logger.getLogger( Log.class.getName() );

   /* public static void initLogConfig(String name) {
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
        String fileName = df.format(new Date()).toString();
        //获取当前机器的IP地址
        String ip;
        FileAppender append;
        try {
            ip = java.net.InetAddress.getLocalHost().getHostAddress();
            String IPath = System.getProperty("D://workspace//ERP_KeyWordsFramework//log")  + fileName + "//" + ip;
            //根据IP地址创建目录，使用“类名+时间戳”的形式命名log文件
            File tmp = new File(IPath);
            if(!tmp.getAbsoluteFile().exists()) {
                tmp.getAbsoluteFile().mkdirs();
            }
            //修改log4j配置文件中所指定的log文件保存的路径
            append = (FileAppender) Logger.getRoot().getAppender("File");
            String logPath = IPath + "//" + name;
            append.setFile(logPath);
            append.activateOptions();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    //定义测试用例开始执行的打印方法，在日志中打印测试用例开始执行的信息
    public static void startTestCase(String testCaseName) {

        Log.info( "----------------------   \"" + testCaseName +" \"开始执行 ---------------" );
    }
    //定义测试用例执行完毕后的打印方法，在日志中打印测试用例执行完毕的信息
    public static void endTestCase(String testCaseName) {
        Log.info( "----------------------   \"" + testCaseName +" \"测试始执行结束 ---------------" );
    }
    //定义打印info级别日志的方法
    public static void info(String message) {
        Log.info(message);
    }
    //定义打印error级别日志的方法
    public static void error(String message) {
        Log.error(message);


    }
    //定义打印debug级别日志的方法
    public static void debug(String message) {
        Log.debug(message);
    }

}
