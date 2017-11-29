package cn.erp.keywords.configuration;
import cn.erp.keywords.testScript.TestContractByExcel;


import cn.erp.keywords.util.Log;
import cn.erp.keywords.util.ObjectMap;
import cn.erp.keywords.util.WaitUitl;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static cn.erp.keywords.util.WaitUitl.waitWebElement;

/**
 * Created by mike.liu on 2017/7/25.
 */
public class SubmitContractKeyWords {
    //声明静态WebDriver对象，用于在此类中相关Driver的操作
    public static WebDriver driver;
    //声明存储定位表达配置文件的objectmap对象
    private  static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile );
    private static WebElement currentTabIframe1;

    static {
        //指定Log4j配置文件为log4j.xml
        DOMConfigurator.configure( "log4j.xml" );
    }
    /*此方法的名称对应Excel文件“关键字”列中的open_browser关键字
    * Excel文件“操作值”列中的内容用于指定测试用例用何种浏览器运行测试用例。IE表示启动IE浏览器运行测试用例，
    * Firefox表示启动火狐浏览器，chrome表示启动chrome浏览器进行测试
     */
    public static void open_browser(String browserName) {
        if (browserName.equals( "ie" )) {
            System.setProperty( "webdriver.ie.driver","D:\\workspace\\Selenium_Automated\\driver\\IEDriverServer.exe" );
            driver = new InternetExplorerDriver(  );
            Log.info("IE浏览器实例已经声明");

        } else if (browserName.equals( "firefox" )) {
            System.setProperty( "webdriver.firefox.driver","D:\\workspace\\Selenium_Automated\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
            Log.info("火狐浏览器实例已经声明");
        } else {
            System.setProperty( "webdriver.chrome.driver","D:\\workspace\\Selenium_Automated\\driver\\chromedriver.exe");
            driver = new ChromeDriver(  );
            Log.info("谷歌浏览器实例已经声明");
        }

    }
    //此方法的名称对应Excel文件“关键字”列中的navigate关键字
    //读取Excel文件“操作值”列中的网址内容作为浏览器访问的网址
    public static void navigate(String url) {
        driver.get(url);
        Log.info("浏览器访问网址"+url);
    }
    /*此方法的名称对应Excel文件“关键字”列中的input_userName关键字
    *读取Excel文件“操作值”列中的邮箱用户名称，作为登录用户名的输入内容
    */
    public static void input_userName(String userName) {


        try {

            driver.findElement( objectMap.getLocator( "login.username" ) ).clear();
            Log.info("清除用户名输入框的所有内容");
            driver.findElement( objectMap.getLocator( "login.username" ) ).sendKeys( userName );
            Log.info("在输入框输入用户名:"+userName);
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("在用户名输入框输入"+userName+"出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的input_passWord关键字
    *读取Excel文件“操作值”列中的邮箱用户名称，作为登录用户名的输入内容
    */
    public static void input_passWord(String passWord) {

        try {
            driver.findElement( objectMap.getLocator( "login.password" ) ).clear();
            Log.info("清除用户密码输入框的所有内容");
            driver.findElement( objectMap.getLocator( "login.password" ) ).sendKeys( passWord );
            Log.info("在输入框输入用户密码:"+passWord);
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("在用户密码输入框输入"+passWord+"出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_login关键字
    * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式
    * （均传入一个参数）
    */
    public static void click_login(String string){
        try {
            driver.findElement( objectMap.getLocator( "login.button" ) ).click();
            Log.info("单击登录按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击登录按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /* 此方法的名称对应Excel文件“关键字”列中的waitfor_element关键字
    * 用于显示等待页面元素出现在页面中。函数读取Excel文件“操作值”列中的表达式作为函数，objectmap对象的getLocator方法会
    * 根据函数只在配置文件中查找key值对应的定位表达式。
     */
    public static void WaitFor_Element (String xpathExpression) {
        try {
            //调用封装的waitwebElement函数显示等待页面元素是否出现
            waitWebElement(driver,objectMap.getLocator("homepage.logoutlink"));
            Log.info("显式等待元素出现成功，元素是:退出系统");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("显式等待元素出现异常,具体异常信息："+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_CustomerRelations关键字
   * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式
   * （均传入一个参数）
   */
    public static void click_CustomerRelations(String string){
        try {
            driver.findElement( objectMap.getLocator( "homepage.CustomerRelations" ) ).click();
            Log.info("单击客户关系链接");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击客户关系链接时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的sleep关键字,用于等待操作，暂停几秒，函数参数是以毫秒为单位的等待时间,
    *参数sleepTime表示暂停的毫秒数，参数String为无实际值传入的参数，仅为了通过反射机制统一地使用两个函数参数来调用此函数
     */
    public static void sleep(String sleepTime) {
        try {
            WaitUitl.sleep( Integer.parseInt( sleepTime ) );
            Log.info("休眠"+Integer.parseInt( sleepTime )/1000+"秒成功");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("线程休眠时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_contracts关键字，用于单击合同管理按钮。
    *需要遍历菜单项，找到合同管理字段
    * 使用两个函数参数来调用此函数
     */
    public static void click_contracts(String string) {
        try {
            /*遍历菜单项，并存储在list容器中，
            *当找到合同管理菜单的时候，点击该菜单
             */

            List<WebElement> eles = driver.findElements( By.tagName("span"));

            for (WebElement ele : eles) {
                String id = ele.getAttribute("id");

                if (id.startsWith("leftTree") && ele.getText().equals("合同管理")) {
                    //System.out.println(ele.getText());
                    ele.click();
                    Log.info( "单击合同按钮成功" );
                    break;
                }
            }

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击合同管理按钮出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的input_Search关键字
   *读取Excel文件“操作值”列中的合同编号，作为查找的输入内容
   */
    public static void input_Search(String Search) {


        WebElement currentTabIframe1 = findCurrentTabframe();

        if (currentTabIframe1 != null) {
            System.out.println( "currentTabIframe1:" + currentTabIframe1 );
            driver.switchTo().frame( currentTabIframe1 );
        }


        try {
            driver.findElement( objectMap.getLocator( "SubmitContractpage.Search" ) ).clear();
            Log.info("清除合同编号输入框的所有内容");
            driver.findElement( objectMap.getLocator( "SubmitContractpage.Search" ) ).sendKeys( Search );
            Log.info("在输入框输入合同编号:"+Search);
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("在合同编号输入框输入合同编号出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_BtnSearch关键字
    * 实现单击查找按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式
    * （均传入一个参数）
    */
    public static void click_BtnSearch(String string){
        try {

            driver.findElement( objectMap.getLocator( "SubmitContractpage.BtnSearch" ) ).click();
            Log.info("单击查找按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击查找按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

   /* //等待搜索结果出现
    public static void sleep_search(String sleepTime) {
        try {
            WaitUitl.sleep( Integer.parseInt( sleepTime ) );
            Log.info("休眠"+Integer.parseInt( sleepTime )/1000+"秒成功");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("线程休眠时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }*/
    /*此方法的名称对应Excel文件“关键字”列中的click_Submit关键字
    * 提交合同
    */
    public static void click_Submit(String string){
        try {
            driver.findElement( objectMap.getLocator( "SubmitContractpage.Submit" ) ).click();
            Log.info("单击提交合同按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击提交合同按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_Confirm
    * 点击提示信息的确定按钮
     */
    public static void click_Confirm(String string) {
        try {
            WebElement e = (new WebDriverWait( driver, 10 )).until( new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver d) {
                    d.getTitle().equals( "成功" );
                    return d.findElement( By.cssSelector( ".l-dialog-btn-inner" ) );  //点击成功提示信息的确定按钮
                }
            } );

            e.click();
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info( "单击提示信息的确定按钮时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的Assert_String关键字,参数assertString为要断言的字符串内容，参数String
    *为无实际值传入的参数，仅为了通过反射机制统一地使用两个函数参数来调用此函数
      */

   /* public static void Assert_String(String string,String assertString) {
        try {
            Assert.assertTrue(driver.getPageSource().contains( assertString ));
            Log.info("成功断言关键字"+assertString +"");
        } catch (AssertionError e) {
            TestSuiteByExcel.testResult = false;
            Log.info("出现断言失败，具体断言失败信息:"+e.getMessage());
            System.out.println("断言失败");
        }
    }
     /*此方法的名称对应Excel文件“关键字”列中的click_Approval关键字
    * 审批合同，客服点击审批流链接
    */
   public static void click_Approval(String string){
       try {
           driver.switchTo().frame("home");
           driver.findElement( objectMap.getLocator( "ApprovalContractpage.ApprovalProcess" ) ).click();
           Log.info("单击审批合同按钮");

       } catch (Exception e) {
           TestContractByExcel.testResult = false;
           Log.info("单击审批合同按钮时出现异常，具体异常信息:"+e.getMessage());
           e.printStackTrace();
       }
   }
    /*//等待审批流显示同意按钮
    public static void sleep_btnAgree(String sleepTime) {
        try {
            WaitUitl.sleep( Integer.parseInt( sleepTime ) );
            Log.info("休眠"+Integer.parseInt( sleepTime )/1000+"秒成功");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("线程休眠时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }*/
    /*此方法的名称对应Excel文件“关键字”列中的click_btnAgree关键字
   * 审批合同，客服点击审批流的确定按钮
   */
    public static void click_btnAgree(String string){
        try {
            // 得到当前窗口的句柄
            String currentWindow = driver.getWindowHandle();
            // 得到所有窗口的句柄
            Set<String> handles = driver.getWindowHandles();

            for (String handle : handles) {
                System.out.println(handle);
            }
            String secondWindow = "";
            Iterator<String> it = handles.iterator();
            while (it.hasNext()) {
                String handle = it.next();
                if (currentWindow.equals(handle))
                    continue;
                driver = driver.switchTo().window(handle);
                secondWindow = handle;

            }

            // switchtoWindows.execute("platform");

            // 点击同意按钮
           driver.findElement( objectMap.getLocator( "ApprovalContractpage.btnAgree" ) ).click();
            Log.info("单击同意按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击同意按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    //等待审批流显示填写意见框信息
    public static void sleep_dataFormSave(String sleepTime) {
        try {
            WaitUitl.sleep( Integer.parseInt( sleepTime ) );
            Log.info("休眠"+Integer.parseInt( sleepTime )/1000+"秒成功");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("线程休眠时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_dataFormSave
   * 点击填写意见的确定按钮
    */
    public static void click_dataFormSave(String string) {
        try {
            //查找填写意见弹框
            Set<String> handles1 = driver.getWindowHandles();

            for (String handle : handles1) {

            }

            List<WebElement> eles = driver.findElements(By.tagName("iframe"));

            for (WebElement ele : eles) {
                String id = ele.getAttribute("id");

                if (id.startsWith("ligerwindow")) {
                    driver.switchTo().frame(ele);
                    break;
                }
            }
            //点击填写意见弹框的确定按钮
            driver.findElement( objectMap.getLocator( "ApprovalContractpage.dataFormSave" ) ).click();
            Log.info("单击填写意见弹框的确定按钮");
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info( "单击提示信息的确定按钮时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_btnDialog关键字
   * 审批合同，客服点击审批流填写意见的提示信息确定按钮
   */
    public static void click_btnDialog(String string){
        try {
            WebElement btnDialog = (new WebDriverWait(driver, 10)) .until(
                    new ExpectedCondition< WebElement>(){
                        @Override
                        public WebElement apply( WebDriver d) {
                            // Assert.assertTrue(d.getClass().equals("执行任务成功!"));
                            return d.findElement(By.cssSelector(".l-dialog-btn-inner"));  //点击成功提示信息的确定按钮
                        }
                    }
            );

            btnDialog.click();

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击审批合同的同意按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_BuyDetail关键字
       * 点击合同页面的创建购买记录按钮
       */
    public static void click_BuyDetail(String string){
        try {
            driver.findElement( objectMap.getLocator( "BuyDetail.Add" ) ).click();
            Log.info("单击创建购买记录按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击创建购买记录按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
/*此方法的名称对应Excel文件“关键字”列中的click_findHref关键字
 * 点击查找双录按钮
*/
    public static void click_findHref(String string){
        try {
            WebElement elem = findHref();

            if(elem != null)
            {
                elem.click();
            }
            Log.info("单击查找双录按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击查找双录按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

/*此方法的名称对应Excel文件“关键字”列中的click_follow关键字
 * 选择双录信息
*/
    public static void click_follow(String string){
        try {

            //选择双录信息
            WebElement odd = (new WebDriverWait(driver, 10)).until( new ExpectedCondition<WebElement>() {
                @Override
                public WebElement apply(WebDriver d) {
                    d.getTitle().equals("选择双录跟进记录");
                    System.out.print( d.getTitle() );
                    return d.findElement(By.cssSelector(".followRecordId"));  //选择双录信息
                }
            });

            odd.click();
            Log.info("单击选择双录按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击选择双录按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的Click_btnfollow关键字
     * 点击选择双录的确定按钮
    */
    public static void Click_btnfollow(String string){
        try {
            WebElement elem1 = Btn();

            if(elem1 != null)
            {
                elem1.click();
            }
            Log.info("单击选择双录的确定按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击选择双录的确定按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的input_subscribedNetSubsAmount
    * 输入净认/申购金额
     */
    public static void input_subscribedNetSubsAmount(String subscribedNetSubsAmount){
        try {
            driver.findElement( objectMap.getLocator( "BuyDetail.subscribedNetSubsAmount" ) ).sendKeys(subscribedNetSubsAmount);
            Log.info("净认/申购金额");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("净认/申购金额时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的Input_Time
    * 点击提示信息的确定按钮
     */
    public static void Input_Time(String string) {
        try {
            // 创建一个dataformat对象
            SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 利用Date()获取当前时间
            Date date = new Date();
            // 格式化时间,并用String对象存储
            String date1 = dataFormat.format(date);
            //System.out.println(date1);
           // String js = "document.getElementById('subscriptionTime').focus();";
           // ((JavascriptExecutor)driver).executeScript(js);
            Thread.sleep(2000);

            driver.findElement( objectMap.getLocator("BuyDetail.InputTime" )).sendKeys(date1);



            Log.info("输入认购时间");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info( "输入认购时间时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_dpOkInput
    * 点击提示信息的确定按钮
     */
    public static void click_dpOkInput(String string) {
        try {

            driver.switchTo().defaultContent();
            WebElement elem = findInputDataIframe();

            if(elem != null)
            {
                System.out.println(elem);
                driver.switchTo().frame(elem);
            }
            driver.findElement( objectMap.getLocator( "BuyDetail.dpOkInput" ) ).click();
            driver.switchTo().parentFrame();

           // driver.switchTo().frame(currentTabIframe1);
            Thread.sleep(2000);

            WebElement currentTabIframe1  = findCurrentTabframe();

            System.out.println("currentTabIframe1 = " + currentTabIframe1);

            if(currentTabIframe1 != null)
            {
                System.out.println(currentTabIframe1);
                driver.switchTo().frame(currentTabIframe1);
            }
            //driver.switchTo().frame("20000096841542");
            Log.info( "单击认购时间的确定按钮" );

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info( "单击认购时间按钮时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }

    }

    /*此方法的名称对应Excel文件“关键字”列中的click_Save关键字
* 点击保存按钮
*/
   public static void click_Save(String string){
        try {

           //driver.switchTo().defaultContent();
            //Thread.sleep(2000);


            //driver.switchTo().frame(currentTabIframe1);
            Thread.sleep(3000);


            WebElement elem = findsave();

            if(elem != null)
            {
                System.out.println("elem:"+elem);
                elem.click();
            }
           // driver.findElement( objectMap.getLocator( "BuyDetail.Save" ) ).click();
            Log.info("单击保存按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击保存按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_PeBtn关键字
   * 点击新建打款心得按钮
   */
    public static void click_PeBtn(String string){
        try {

            WebElement elem3 = PEBtn();

            System.out.println("elem3:"+ elem3);
            if(elem3 != null)
            {
                elem3.click();
            }
            Log.info("单击新建打款心得按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击新建打款心得按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的input_Experience关键字
   * 输入打款心得的内容
   */
    public static void input_Experience(String body_string){
        try {

            driver.switchTo().frame("ueditor_0");
            driver.findElement( By.tagName( "body" ) ).sendKeys( body_string );


            driver.switchTo().parentFrame();
            Log.info("输入打款心得的内容");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("输入打款心得的内容时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_SavePEBtn关键字
* 点击保存打款心得按钮
*/
    public static void click_SavePEBtn(String String){
        try {

            WebElement SaveBtn = SavePEBtn();

            if(SaveBtn != null)
            {
                SaveBtn.click();
            }


            Log.info("点击保存心得的保存按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("点击保存心得的保存按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_Edit关键字
  * 点击编辑按钮
  */
    public static void click_Edit(String string){
        try {

            driver.findElement( objectMap.getLocator( "BuyDetail.Edit" ) ).click();
            Log.info("单击编辑按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击编辑按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }


    /*此方法的名称对应Excel文件“关键字”列中的click_showFile
    * 点击上传打款凭条的上传按钮
     */

    public static void click_showFile(String string) {
        try {
            WebElement elem2 = findHref3();

            if(elem2 != null)
            {
                elem2.click();
            }

            WebElement currentTabIframe  = findUploadDiaglogframe();

            System.out.println("currentTabIframe = " + currentTabIframe);

            if(currentTabIframe != null)
            {
                System.out.println(currentTabIframe);
                driver.switchTo().frame(currentTabIframe);
            }

            Thread.sleep( 5000 );
            WebElement elem = findHrefById( "idcardupload" );

            if (elem != null) {
                elem.click();
                Log.info( "点击上传身份证的上传按钮" );

                Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\身份证.jpg" );

                Thread.sleep( 5000 );
                elem = findHrefById( "bank" );

                if (elem != null) {
                    elem.click();
                    Log.info( "点击上传银行卡的上传按钮" );

                    Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\银行卡.jpg" );
                }
                Thread.sleep( 5000 );
                elem = findHrefById( "pay" );

                if (elem != null) {
                    elem.click();
                    Log.info( "点击上传打款凭条的上传按钮" );

                    Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\打款凭条.jpg" );
                }
            }


            Log.info( "点击上传打款凭条的上传按钮" );

            }catch(Exception e){
            TestContractByExcel.testResult = false;
                Log.info( "单击上传打款凭条按钮时出现异常，具体异常信息:" + e.getMessage() );
                e.printStackTrace();
            }
        }

    /*此方法的名称对应Excel文件“关键字”列中的click_idcardupload
* 点击上传身份证附件
*/
    /*public static void click_idcardupload(String string, WebElement elem) {
        try {

            elem = findHrefById( "idcardupload" );

            if (elem != null) {
                elem.click();
                Log.info( "点击上传身份证的上传按钮" );

                Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\身份证.jpg" );
            }
        }catch (IOException e) {
            TestRemittanceByExcel.testResult = false;
            Log.info( "点击上传身份证的上传按钮时出现异常，具体异常信息:" + e.getMessage() );
                e.printStackTrace();
            }

        }

    /*此方法的名称对应Excel文件“关键字”列中的click_bank
* 点击上传身份证附件
*/
  /*  public static void click_bank(String string, WebElement elem) {
        try {
            WebElement currentTabIframe  = findUploadDiaglogframe();

            System.out.println("currentTabIframe = " + currentTabIframe);

            if(currentTabIframe != null)
            {
                System.out.println(currentTabIframe);
                driver.switchTo().frame(currentTabIframe);
            }

            elem = findHrefById( "bank" );

            if (elem != null) {
                elem.click();
                Log.info( "点击上传银行卡的上传按钮" );

                Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\银行卡.jpg" );
            }
        }catch (IOException e) {
            TestRemittanceByExcel.testResult = false;
            Log.info( "点击上传银行卡的上传按钮时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }

    }
    /*此方法的名称对应Excel文件“关键字”列中的click_pay关键字
* 点击上传身份证附件
*/
  /*  public static void click_pay(String string, WebElement elem) {
        try {

            elem = findHrefById( "pay" );

            if (elem != null) {
                elem.click();
                Log.info( "点击上传打款凭条的上传按钮" );

                Runtime.getRuntime().exec( "D:\\workspace\\Erp-Automated-Test\\upload.exe" + " " + "chrome" + " " + "D:\\图片\\打款凭条.jpg" );
            }
        }catch (IOException e) {
            TestRemittanceByExcel.testResult = false;
            Log.info( "点击上传打款凭条的上传按钮时出现异常，具体异常信息:" + e.getMessage() );
            e.printStackTrace();
        }

    }

    /*此方法的名称对应Excel文件“关键字”列中的click_Uploadclose关键字
* 点击上传附件弹窗的关闭按钮
*/
    public static void click_Uploadclose(String string){
        try {
            driver.switchTo().defaultContent();
            WebElement currentTabIframe1  = findCurrentTabframe();

            System.out.println("currentTabIframe1 = " + currentTabIframe1);

            if(currentTabIframe1 != null)
            {
                System.out.println(currentTabIframe1);
                driver.switchTo().frame(currentTabIframe1);
            }

            driver.findElement( objectMap.getLocator( "BuyDetail.Close" ) ).click();
            Log.info("单击上传附件弹窗的关闭按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击上传附件弹窗的关闭按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
    /*此方法的名称对应Excel文件“关键字”列中的click_BtnRebate关键字
   * 点击立即创建返佣申请的不返佣按钮
   */
    public static void click_BtnRebate(String string){
        try {

            WebElement Rebate = BtnRebate();

            if(Rebate != null)
            {
                Rebate.click();
            }
            Log.info("单击立即创建返佣申请的不返佣按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击立即创建返佣申请的不返佣按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_showRiskFileView关键字
 * 点击上传风险担当声明函附件的按钮
 */
    public static void click_showRiskFileView(String string){
        try {
            WebElement elem4 = findHref1();

            System.out.println("elem4:"+elem4);
            if(elem4 != null)
            {
                elem4.click();
            }

            WebElement currentTabIframe3  = findUploadDiaglogframe1();

            System.out.println("currentTabIframe3 = " + currentTabIframe3);

            if(currentTabIframe3 != null)
            {
                System.out.println(currentTabIframe3);
                driver.switchTo().frame(currentTabIframe3);
            }
            Log.info("单击上传风险担当声明函附件的按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击上传风险担当声明函附件的按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的upload_showRiskFileView关键字
* 选择风险担当声明函附件进行上传
*/
    public static void upload_showRiskFileView(String string){
        try {
            driver.findElement(By.tagName("label")).click();

            Runtime.getRuntime().exec("D:\\workspace\\Erp-Automated-Test\\upload.exe"+ " " + "chrome" + " " + "D:\\图片\\风险担当声明函.jpg");
            Log.info("选择风险担当声明函附件");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("选择风险担当声明函附件时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的click_showRiskAdviceNoteFileView关键字
* 点击上传风险担当声明函附件的按钮
*/
    public static void click_showRiskAdviceNoteFileView(String string){
        try {
            WebElement elem5 = findHref2();

            System.out.println("elem5:"+elem5);
            if(elem5 != null)
            {
                elem5.click();
            }

            WebElement currentTabIframe4  = findUploadDiaglogframe2();

            System.out.println("currentTabIframe4 = " + currentTabIframe4);

            if(currentTabIframe4 != null)
            {
                System.out.println(currentTabIframe4);
                driver.switchTo().frame(currentTabIframe4);
            }
            Log.info("单击上传风险担当声明函附件的按钮");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("单击上传风险担当声明函附件的按钮时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /*此方法的名称对应Excel文件“关键字”列中的upload_showRiskAdviceNoteFileView关键字
* 选择风险等级通知书附件进行上传
*/
    public static void upload_showRiskAdviceNoteFileView(String string){
        try {
            driver.findElement(By.tagName("label")).click();

            Runtime.getRuntime().exec("D:\\workspace\\Erp-Automated-Test\\upload.exe"+ " " + "chrome" + " " + "D:\\图片\\风险等级通知书.jpg");
            Log.info("选择风险等级通知书附件");

        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("选择风险等级通知书附件时出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }


    /*此方法的名称对应Excel文件“关键字”列中的close_browser关键字

     */
    public static void close_browser(String string) {
        try {
            System.out.println("浏览器关闭函数被执行");
            Log.info("关闭浏览器窗口");
            driver.quit();
        } catch (Exception e) {
            TestContractByExcel.testResult = false;
            Log.info("关闭浏览器出现异常，具体异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }

    //点击打款记录页面的查找双录按钮
    private static WebElement findHref() {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String onclick = ele.getAttribute("onclick");
            System.out.println("onclick: " + onclick);

            if (onclick != null && onclick.contains("creatFollowRecordTable()")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    private static WebElement Btn() {

        //遍历选择双录弹出框，当找到保存按钮的时候，点击保存按钮
        List<WebElement> eles = driver.findElements( By.tagName("div"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String id = ele.getAttribute("class");

            if (id.startsWith("l-dialog-btn-inner") && ele.getText().equals("保存")) {
                System.out.println(ele.getText());
                ele.click();
                break;
            }
        }
        return eleRet;
    }

    //遍历新建返佣或不返佣弹出框，当找到不返佣按钮的时候，点击该按钮
    private static WebElement BtnRebate() {


        List<WebElement> eles = driver.findElements( By.tagName("div"));

        System.out.println(eles.size() + " ======");

        WebElement RebateRet = null;

        for (WebElement ele : eles) {
            String id = ele.getAttribute("class");

            if (id.startsWith("l-dialog-btn-inner") && ele.getText().equals("【不返佣】")) {
                System.out.println(ele.getText());
                ele.click();
                break;
            }
        }
        return RebateRet;

    }
    private static WebElement SavePEBtn() {

        //遍历选择双录弹出框，当找到保存按钮的时候，点击保存按钮
        List<WebElement> eles = driver.findElements( By.tagName("div"));

        System.out.println(eles.size() + "SavePEBtn ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String id = ele.getAttribute("class");

            System.out.println("SavePEBtn class:"+ id);
            if (id.startsWith("l-dialog-btn-inner") && ele.getText().equals("保存")) {
                System.out.println(ele.getText());
                ele.click();
                break;
            }
        }
        return eleRet;
    }

    private static WebElement PEBtn() {

        //遍历新建打款心得弹出框，当找到录入打款心得按钮的时候，点击该按钮
        List<WebElement> eles = driver.findElements( By.tagName( "div" ) );

        System.out.println( eles.size() + " ======" );

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String id = ele.getAttribute( "class" );

            if (id.startsWith( "l-dialog-btn-inner" ) && ele.getText().equals( "录入打款心得" )) {
                System.out.println( ele.getText() );
                ele.click();
                break;
            }
        }
        return eleRet;
    }
    //查找打款凭条附件上传按钮
    private static WebElement findHref3() {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String onclick = ele.getAttribute("onclick");
            System.out.println("onclick: " + onclick);

            if (onclick != null && onclick.contains("showFileViewBuydetail")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    //查找打款凭条附件的弹框信息
    private static WebElement findUploadDiaglogframe() {
        List<WebElement> eles = driver.findElements(By.tagName("iframe"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String src = ele.getAttribute("src");
            String id = ele.getAttribute("id");
            System.out.println("打款凭条附件src: " + src + "id: " + id);

            if (id.startsWith("ligerwindow")&& src.contains("buydetailRelationList")){
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    //查找风险担当声明函添加附件按钮
    private static WebElement findHref1() {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String onclick = ele.getAttribute("onclick");
            System.out.println("onclick: " + onclick);

            if (onclick != null && onclick.contains("showRiskFileView")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    //查找风险等级通知书添加附件按钮
    private static WebElement findHref2() {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String onclick = ele.getAttribute("onclick");
            System.out.println("onclick: " + onclick);

            if (onclick != null && onclick.contains("showRiskAdviceNoteFileView")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    //查找风险担当声明函的弹框信息
    private static WebElement findUploadDiaglogframe1() {
        List<WebElement> eles = driver.findElements(By.tagName("iframe"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String src = ele.getAttribute("src");
            String id = ele.getAttribute("id");
            System.out.println("查找风险担当声明函的弹框信息src: " + src + "id: " + id);

            if (id.startsWith("ligerwindow")&& src.contains("fileRelationList")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    //查找风险等级通知书的弹框信息
    private static WebElement findUploadDiaglogframe2() {
        List<WebElement> eles = driver.findElements(By.tagName("iframe"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String src = ele.getAttribute("src");
            String id = ele.getAttribute("id");
            System.out.println("查找风险等级通知书的弹框信息src: " + src + "id: " + id);

            if (id.startsWith("ligerwindow")&& src.contains("fileRelationList")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

    private static WebElement findCurrentTabframe() {
        List<WebElement> eles = driver.findElements(By.tagName("iframe"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String src = ele.getAttribute("src");
            String id = ele.getAttribute("id");
            System.out.println("src: " + src + "id: " + id);

            if (src != null && src.contains("contract")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }


    //上传打款凭条的id
    private static WebElement findHrefById(String idName) {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String id = ele.getAttribute("id");
            System.out.println("id: " + id);

            if (id != null && id.contains(idName)) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }
    //认购时间的弹框信息
    private static WebElement findInputDataIframe() {
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            System.out.println(window);
        }

        List<WebElement> eles = driver.findElements(By.tagName("iframe"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String src = ele.getAttribute("src");
            String id = ele.getAttribute("id");
            String hidefocus = ele.getAttribute("hidefocus");
            System.out.println("src: " + src + "id: " + id + "hidefocus:" + hidefocus);

            if (hidefocus != null && hidefocus.equalsIgnoreCase("true")) {
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }

        private static WebElement findsubmit() {
            List<WebElement> eles = driver.findElements(By.tagName("a"));

            System.out.println(eles.size() + " ======");

            WebElement eleRet = null;

            for (WebElement ele : eles) {
                String onclick = ele.getAttribute("class");
                System.out.println("class: " + onclick);

                if (onclick != null && onclick.contains("提交")) {
                    eleRet = ele;
                    break;
                }
            }

            return eleRet;
        }

    private static WebElement findsave() {
        List<WebElement> eles = driver.findElements(By.tagName("a"));

        System.out.println(eles.size() + " ======");

        WebElement eleRet = null;

        for (WebElement ele : eles) {
            String onclick = ele.getAttribute("id");
            System.out.println("id: " + onclick);

            if (onclick.startsWith( "dataFormSave" ) && ele.getText().equals("保存")) {
                System.out.println("dataFormSave:"+ele.getText());
                eleRet = ele;
                break;
            }
        }

        return eleRet;
    }
}