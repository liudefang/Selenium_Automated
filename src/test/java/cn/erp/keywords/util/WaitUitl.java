package cn.erp.keywords.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mike.liu on 2017/7/25.
 */
public class WaitUitl {
    //用于测试执行过程中暂停程序执行的休眠方法
    public static void sleep(long millisecond) {
        try {
            //线程休眠millisecond参数定义的毫秒数
            Thread.sleep(millisecond);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    //显示等待页面元素出现的封装方法，参数为页面元素的Xpath定位字符串
    public static void waitWebElement(WebDriver driver, String xpathExpression){
        WebDriverWait wait = new WebDriverWait(driver,10);
        //调用expectedConditions的presenceOfElementLocated方法判定页面元素是否出现
        wait.until( ExpectedConditions.presenceOfElementLocated( By.xpath(xpathExpression)));

    }
    //显示等待页面元素出现的封装方法，参数为表示页面元素的By对象，此函数可以支持更多定位表达式
    public static void waitWebElement(WebDriver driver,By by) {
        WebDriverWait wait = new WebDriverWait( driver,10 );
        //调用expectedconditions方法判断页面元素是否出现
        wait.until( ExpectedConditions.presenceOfElementLocated( by ) );
    }
}

