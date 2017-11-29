package cn.erp.keywords.testScript;
import cn.erp.keywords.configuration.Constants;
import cn.erp.keywords.configuration.SubmitContractKeyWords;
import cn.erp.keywords.util.ExcelUtil;
import cn.erp.keywords.util.Log;
import org.apache.log4j.xml.DOMConfigurator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

/**
 * Created by mike.liu on 2017/7/27.
 */
public class TestContractByExcel {
    public static Method method[];
    public static String keyword;
    public static String value;
    public static SubmitContractKeyWords keyWordsaction;
    public static int testStep;
    public static int testLastStep;
    public static String testCaseID;
    public static String testCaseRunFlag;
    public static boolean testResult;


    @Test
    public void testTestSuite() throws Exception{
        //声明一个关键动作类的实例
        keyWordsaction = new SubmitContractKeyWords();
        //使用Java的反射机制获取KeyWord类的所有方法对象
        method = keyWordsaction.getClass().getMethods();
        //定义Excel关键文件的路径
        String excelFilePath = Constants.Path_ExcelFile;
        //设定读取Excel文件中的“提交合同”Sheet为操作目标
        ExcelUtil.setExcelFile( excelFilePath );
        //读取“测试用例集合”Sheet中的测试用例总数
        int testCasesCount = ExcelUtil.getRowCount( Constants.Sheet_TestSuite );
        //使用for循环，执行所有标记为“y”的测试用例
        for(int testCaseNo = 1; testCaseNo <= testCasesCount;testCaseNo ++) {
            //读取“测试用例集合”Sheet中每行的测试用例序号
         testCaseID = ExcelUtil.getCellData( Constants.Sheet_TestSuite,testCaseNo,Constants.Col_TestCaseID );
        //读取“测试用例集合”Sheet中每行的是否运行列中的值
         testCaseRunFlag = ExcelUtil.getCellData( Constants.Sheet_TestSuite,testCaseNo,Constants.Col_RunFlag );

         //如果是否运行列中的值为“y”,则执行测试用例中的所有步骤
         if(testCaseRunFlag.equalsIgnoreCase( "y" )) {
             //在日志中打印测试用例开始执行
             Log.startTestCase(testCaseID);
             //设定测试用例的当前结果为true，即表明测试执行成功
             testResult = true;
             //在“提交合同”Sheet中，获取当前要执行测试用例的第一个步骤所在行行号
         testStep = ExcelUtil.getFirstRowContainsTestCaseID( Constants.Sheet_TestSteps,testCaseID,Constants.Col_TestCaseID );
         //在“提交合同”Sheet中，获取当前要执行测试用例的最后一个步骤所在行行号
             testLastStep = ExcelUtil.getTestCaseLastStepRow( Constants.Sheet_TestSteps,testCaseID,testStep );
             //遍历测试用例中的所有测试步骤
             for(;testStep < testLastStep;testStep++) {
              //从“提交合同”Sheet中读取关键字和操作值，调用execute_Action方法
                 keyword = ExcelUtil.getCellData( Constants.Sheet_TestSteps,testStep,Constants.Col_KeyWordAction );
                 //在日志文件中打印关键字信息
                 Log.info("从Excel文件读取到的关键字是:"+keyword);

                 value = ExcelUtil.getCellData( Constants.Sheet_TestSteps,testStep,Constants.Col_ActionValue);

                 //在日志文件中打印操作值信息
                 Log.info("从Excel文件中读取的操作值是："+value);
                 execute_Actions();
                 if(testResult == false) {
                     /*如果测试用例的任何一个测试步骤执行失败，则测试用例集合Sheet中的当前执行
                     *测试用例的执行结果设定为“测试执行失败”
                      */
                     ExcelUtil.setCellData("测试用例集合",testCaseNo,Constants.Col_TestSuiteTestResult,"测试执行失败" );
                     //在日志中打印测试用例执行完毕
                     Log.endTestCase( testCaseID );
                     /*当前测试用例出现执行失败的步骤，则整个测试用例设定为失败状态，break语句跳出当前的for循环，
                     *继续执行测试用例集合中的下一个测试用例
                      */
                     break;

                 }
                 if (testResult == true) {
                     /*如果测试用例的所有步骤执行成功，则会在测试用例集合Sheet中的当前执行测试用例的执行结果
                     *设定为“测试执行成功”
                      */
                     ExcelUtil.setCellData(Constants.Sheet_TestSuite,testCaseNo,Constants.Col_TestSuiteTestResult,"测试执行成功" );
                 }
             }

         }
        }

    }
    private static void execute_Actions() {
        try {
            for (int i = 0; i< method.length; i++) {
                /*使用反射的方式，找到关键字对应的测试方法，并使用value（操作值）
                *作为测试方法的函数值进行调用
                 */
                if(method[i].getName().equals( keyword )) {
                    method[i].invoke( keyWordsaction,value );
                    if (testResult == true) {
                        /*当前测试步骤执行成功，在“提交合同”Sheet中，会将当前执行的测试步骤结果设定为“测试步骤执行成功”

                         */
                        ExcelUtil.setCellData( Constants.Sheet_TestSteps,testStep,Constants.Col_TestStepTestResult,"测试步骤执行成功" );
                        break;
                    } else {
                        /*当前测试步骤执行失败，在“提交合同”Sheet中，会将当前执行的测试步骤结果设定为“测试执行失败”

                         */
                        ExcelUtil.setCellData( Constants.Sheet_TestSteps,testStep,Constants.Col_TestStepTestResult,"测试步骤执行失败" );
                        //测试步骤执行失败，则直接关闭浏览器，不再执行后续的测试步骤
                       SubmitContractKeyWords.close_browser( "" );
                        break;
                    }

                }
            }
        } catch (Exception e) {
            //调用测试方法过程中，若出现异常，则将测试设定为失败状态，停止测试用例执行
            Assert.fail("执行出现异常，测试用例执行失败！");
        }
    }
    @BeforeClass
    public void BeforeClass() {
        //配置Log4j的配置为log4j.xml
        DOMConfigurator.configure( "log4j.xml" );
    }
}
