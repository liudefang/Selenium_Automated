/**
 * Created by mike.liu on 2017/7/27.
 */
package cn.erp.keywords.configuration;
public class Constants {
    //测试数据相关常理设定
    public static final String Path_ExcelFile = "D:\\workspace\\ERP-KeyWordsFramework\\src\\test\\java\\cn\\erp\\keywords\\data\\关键字驱动测试用例.xlsx";
    public static final String Path_ConfigurationFile = "D:\\workspace\\ERP-KeyWordsFramework\\objectMap.properties";
    //测试数据Sheet中的列号常理设定
    //第一列用0表示，测试用例序号列
    public static final int Col_TestCaseID = 0;
    //第四列用3表示，关键字列
    public static final int Col_KeyWordAction = 3;
    //第五列用4表示，操作值列
    public static final int Col_ActionValue = 4;
    //第六列用5表示，测试结果列
    public static final  int Col_TestStepTestResult = 5;



    //测试集合Sheet中的列号常理设定
    public static final int Col_RunFlag = 2;
    //测试结合Sheet中的测试结果列号常理设定，测试用例执行结果列
    public static final int Col_TestSuiteTestResult = 3;
    //测试用例Sheet名称的常量设定
    public static final String Sheet_TestSteps = "购买记录流程";
    public static final String Sheet_TestSteps1 = "产品费率";
    public static final String Sheet_TestSteps2 = "购买记录";
    //测试用例集Sheet的常量设定
    public static final String Sheet_TestSuite = "测试用例集合";
}

