package cn.erp.keywords.configuration;

import cn.erp.keywords.testScript.TestContractByExcel;
import cn.erp.keywords.testScript.TestProductfeeByExcel;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cn.erp.keywords.util.Log;
import cn.erp.keywords.util.ObjectMap;
import cn.erp.keywords.util.WaitUitl;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by mike.liu on 2017/9/4.
 */
public class ProductfeeKeyWords {
	// 声明静态WebDriver对象，用于在此类中相关Driver的操作
	public static WebDriver driver;
	// 声明存储定位表达配置文件的objectmap对象
	private static ObjectMap objectMap = new ObjectMap(Constants.Path_ConfigurationFile);
	private static WebElement currentTabIframe1;
	private static String secondWindow;

	static {
		// 指定Log4j配置文件为log4j.xml
		DOMConfigurator.configure("log4j.xml");
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的open_browser关键字
	 * Excel文件“操作值”列中的内容用于指定测试用例用何种浏览器运行测试用例。IE表示启动IE浏览器运行测试用例，
	 * Firefox表示启动火狐浏览器，chrome表示启动chrome浏览器进行测试
	 */
	public static void open_browser(String browserName) {
		if (browserName.equals( "ie" )) {
			System.setProperty( "webdriver.ie.driver","D:\\workspace\\ERP-KeyWordsFramework\\driver\\IEDriverServer.exe" );
			driver = new InternetExplorerDriver(  );
			Log.info("IE浏览器实例已经声明");

		} else if (browserName.equals( "firefox" )) {
			System.setProperty( "webdriver.firefox.driver","D:\\workspace\\ERP-KeyWordsFramework\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			Log.info("火狐浏览器实例已经声明");
		} else {
			System.setProperty( "webdriver.chrome.driver","D:\\workspace\\ERP-KeyWordsFramework\\driver\\chromedriver.exe");
			driver = new ChromeDriver(  );
			Log.info("谷歌浏览器实例已经声明");
		}

	}

	// 此方法的名称对应Excel文件“关键字”列中的navigate关键字
	// 读取Excel文件“操作值”列中的网址内容作为浏览器访问的网址
	public static void navigate(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		Log.info("浏览器访问网址" + url);
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_userName关键字
	 * 读取Excel文件“操作值”列中的邮箱用户名称，作为登录用户名的输入内容
	 */
	public static void input_userName(String userName) {

		try {

			driver.findElement(objectMap.getLocator("login.username")).clear();
			Log.info("清除用户名输入框的所有内容");
			driver.findElement(objectMap.getLocator("login.username")).sendKeys(userName);
			Log.info("在输入框输入用户名:" + userName);
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("在用户名输入框输入" + userName + "出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_passWord关键字
	 * 读取Excel文件“操作值”列中的邮箱用户名称，作为登录用户名的输入内容
	 */
	public static void input_passWord(String passWord) {

		try {
			driver.findElement(objectMap.getLocator("login.password")).clear();
			Log.info("清除用户密码输入框的所有内容");
			driver.findElement(objectMap.getLocator("login.password")).sendKeys(passWord);
			Log.info("在输入框输入用户密码:" + passWord);
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("在用户密码输入框输入" + passWord + "出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_login关键字
	 * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_login(String string) {
		try {
			driver.findElement(objectMap.getLocator("login.button")).click();
			Log.info("单击登录按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击登录按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的login_select关键字
	 * 实现选择某个系统按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void login_select(String string) {
		try {
			driver.findElement(objectMap.getLocator("login.select")).click();
			Log.info("单击登录按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击登录按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的waitfor_element关键字
	 * 用于显示等待页面元素出现在页面中。函数读取Excel文件“操作值”列中的表达式作为函数，objectmap对象的getLocator方法会
	 * 根据函数只在配置文件中查找key值对应的定位表达式。
	 */
	public static void WaitFor_Element(String xpathExpression) {
		try {
			// 调用封装的waitwebElement函数显示等待页面元素是否出现
			WaitUitl.waitWebElement(driver, objectMap.getLocator("homepage.logoutlink"));
			Log.info("显式等待元素出现成功，元素是:退出系统");
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("显式等待元素出现异常,具体异常信息：" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_CustomerRelations关键字
	 * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_CustomerRelations(String string) {
		try {
			driver.findElement(objectMap.getLocator("homepage.CustomerRelations_frdFee")).click();
			Log.info("单击产品中心链接");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击产品中心时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的sleep关键字,用于等待操作，暂停几秒，函数参数是以毫秒为单位的等待时间,
	 * 参数sleepTime表示暂停的毫秒数，参数String为无实际值传入的参数，仅为了通过反射机制统一地使用两个函数参数来调用此函数
	 */
	public static void sleep(String sleepTime) {
		try {
			WaitUitl.sleep(Integer.parseInt(sleepTime));
			Log.info("休眠" + Integer.parseInt(sleepTime) / 1000 + "秒成功");
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("线程休眠时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_simuProduct关键字
	 * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_simuProduct(String string) {

		List<WebElement> eles = driver.findElements(By.tagName("span")); // 遍历菜单项，当找到私募产品菜单的时候，点击该菜单
		for (WebElement ele : eles) {
			String id = ele.getAttribute("id");

			if (id.startsWith("leftTree") && ele.getText().equals("私募产品")) {
				System.out.println(ele.getText());
				ele.click();
				break;
			}
		}

	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_PevcProduct关键字
	 * 实现单击登录按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_PevcProduct(String string) {

		List<WebElement> eles = driver.findElements(By.tagName("span")); // 遍历菜单项，当找到私募产品菜单的时候，点击该菜单
		for (WebElement ele : eles) {
			String id = ele.getAttribute("id");

			if (id.startsWith("leftTree") && ele.getText().equals("PE/VC")) {
				System.out.println(ele.getText());
				ele.click();
				break;
			}
		}

	}
	// Thread.sleep( 2000 );

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_Search关键字 读取Excel文件“操作值”列中的产品编号，作为查找的输入内容
	 */
	public static void input_Search(String crm_code) {
		WebElement currentTabIframe1 = findCurrentTabframe();

		if (currentTabIframe1 != null) {
			System.out.println(currentTabIframe1);
			driver.switchTo().frame(currentTabIframe1);
		}

		try {
			driver.findElement(objectMap.getLocator("ProductFee.search")).clear();
			Log.info("清除私募产品编号输入框的内容");
			driver.findElement(objectMap.getLocator("ProductFee.search")).sendKeys(crm_code);
			Log.info("输入私募产品编号");
			//driver.findElement( objectMap.getLocator( "SubmitContractpage.BtnSearch" ) ).click();
			//Log.info("单击查找按钮");
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入私募产品编号时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_Search关键字 读取Excel文件“操作值”列中的产品编号，作为查找的输入内容
	 */
	public static void input_SearchPE(String crm_code) {
		WebElement currentTabIframe1 = findCurrentTabframePE();

		if (currentTabIframe1 != null) {
			System.out.println(currentTabIframe1);
			driver.switchTo().frame(currentTabIframe1);
		}

		try {
			driver.findElement(objectMap.getLocator("ProductFee.search")).clear();
			Log.info("清除私募产品编号输入框的内容");
			driver.findElement(objectMap.getLocator("ProductFee.search")).sendKeys(crm_code);
			Log.info("输入私募产品编号");
			//driver.findElement( objectMap.getLocator( "SubmitContractpage.BtnSearch" ) ).click();
			//Log.info("单击查找按钮");
		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入私募产品编号时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_BtnSearch关键字
	 * 实现单击查找按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_BtnSearch(String string){
		try {


			driver.findElement( objectMap.getLocator( "SubmitContractpage.BtnSearch" ) ).click();
			Log.info("单击查找按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击查找按钮时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_detail关键字
	 * 实现单击产品明细按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_detail(String string) {

		try {

			driver.findElement(objectMap.getLocator("ProductFee.detail")).click();
			Log.info("单击产品明细按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击产品明细按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_scrollto关键字
	 * 实现滚动条滚动到页面的最下方，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_scrollto(String string) {

		try {

			((JavascriptExecutor) driver).executeScript( "window.scrollTo(0,document.body.scrollHeight)" );

			Log.info("滚动滚动条按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("滚动滚动条时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_addFee关键字
	 * 实现单击添加费率按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_addFee(String string) {

		try {

			driver.findElement(objectMap.getLocator("ProductFee.addFee")).click();
			Log.info("单击添加费率按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("单击添加费率按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_agreementOrgId关键字
	 * 参数word作为Excel文件中操作值的“他方协议主体机构” （均传入一个参数）
	 */
	public static void input_agreementOrgId(String word) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.agreementOrgId")).clear();
			driver.findElement(objectMap.getLocator("ProductFee.agreementOrgId")).sendKeys(word);
			Log.info("输入他方协议主体机构");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入他方协议主体机构出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的select_agreementType关键字
	 * 实现选择我方协议主体操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void select_agreementType(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.agreementType")).click();
			//WebElement agreementType = driver.findElement(objectMap.getLocator("ProductFee.agreementType"));
			//agreementType.findElement(objectMap.getLocator("ProductFee.agreementType_selected")).click();
			Log.info("选择协议类型");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择协议类型时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的select_usAgreementOrgId关键字
	 * 实现选择我方协议主体操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void select_usAgreementOrgId(String string) {

		try {
			WebElement usAgreementOrgId = driver.findElement(objectMap.getLocator("ProductFee.usAgreementOrgId"));
			usAgreementOrgId.findElement(objectMap.getLocator("ProductFee.usAgreementOrgId_selected")).click();
			Log.info("选择我方协议主体");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择我方协议主体时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 创建一个dataformat对象
	static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

	// 利用Date()获取当前时间
	static Date date = new Date();
	static Calendar ca = Calendar.getInstance();
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_validityFrom关键字
	 * 实现添加协议有效期开始日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_validityFrom(String string) {

		// 格式化时间,并用String对象存储
		String date1 = dataFormat.format(date);

		try {
			driver.findElement(objectMap.getLocator("ProductFee.validityFrom")).sendKeys(date1);
			Log.info("添加协议有效期开始日期");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加协议有效期开始日期时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_validityTo关键字
	 * 实现添加协议有效期结束日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_validityTo(String string) {

		ca.add(Calendar.DATE, 13);// 当前日期加13天
		date = ca.getTime();
		String date2 = dataFormat.format(date);

		try {
			driver.findElement(objectMap.getLocator("ProductFee.validityTo")).sendKeys(date2);
			Log.info("添加协议有效期结束日期");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加协议有效期结束日期时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioDate关键字
	 * 实现添加不分期日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioDate(String string) {

		ca.add(Calendar.DATE, 13);// 当前日期加13天
		date = ca.getTime();
		String date2 = dataFormat.format(date);
		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioDate")).sendKeys(date2);
			Log.info("添加不分期日期");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加不分期日期时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_save关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_save(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save-btn")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_income关键字
	 * 选择认购费计算基准为实缴金额 （均传入一个参数）
	 */
	public static void click_income(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.incomeCalculationBenchmark")).click();

			Log.info("选择认购费计算基准为实缴金额");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择认购费计算基准为实缴金额按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_manage关键字
	 * 选择管理费计算基准为实缴金额 （均传入一个参数）
	 */
	public static void click_manage(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.manageCalculationBenchmark")).click();

			Log.info("选择管理费计算基准为实缴金额");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择管理费计算基准为实缴金额按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addsubscribeFee关键字 
	 * 实现点击认购费按钮操作 （均传入一个参数）
	 */
	public static void is_addsubscribeFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addsubscribeFee")).click();

			Log.info("点击认购费按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击认购费按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_addLadderFfee关键字
	 * 实现添加分期认缴比例，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_addLadderFfee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.add-ladder-fee")).click();
			Log.info("点击添加阶梯认购费按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击添加阶梯认购费按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_applyFeePer1关键字
	 * 参数applyFeePer作为输入的第一阶梯的费率 （均传入一个参数）
	 */
	public static void input_subscribe_applyFeePer1(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeePer-first")).sendKeys(applyFeePer);

			Log.info("输入第一阶梯的认购费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第一阶梯的认购费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_dividePro1关键字
	 * 参数dividePro作为第一阶梯的百分比 （均传入一个参数）
	 */
	public static void input_subscribe_dividePro1(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-dividePro-first")).sendKeys(dividePro);

			Log.info("输入第一阶梯收费的百分比");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第一阶梯收费的百分比时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_applyFeeFrom1关键字
	 * 参数applyFeeFrom作为第二阶梯认购金额 （均传入一个参数）
	 */
	public static void input_subscribe_applyFeeFrom1(String applyFeeFrom) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeeFrom-first")).clear();
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeeFrom-first")).sendKeys(applyFeeFrom);

			Log.info("输入第二阶梯认购金额");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第二阶梯认购金额时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_applyFeePer2关键字
	 * 参数applyFeePer作为输入的第2阶梯的费率 （均传入一个参数）
	 */
	public static void input_subscribe_applyFeePer2(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeePer-second")).sendKeys(applyFeePer);

			Log.info("输入第2阶梯的认购费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第2阶梯的认购费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_dividePro2关键字
	 * 参数dividePro作为第2阶梯的百分比 （均传入一个参数）
	 */
	public static void input_subscribe_dividePro2(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-dividePro-second")).sendKeys(dividePro);

			Log.info("输入第2阶梯收费的百分比");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第2阶梯收费的百分比时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_applyFeeFrom2关键字
	 * 参数applyFeeFrom作为第三阶梯认购金额 （均传入一个参数）
	 */
	public static void input_subscribe_applyFeeFrom2(String applyFeeFrom) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeeFrom-three")).clear();
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeeFrom-three")).sendKeys(applyFeeFrom);

			Log.info("输入第三阶梯认购金额");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第三阶梯认购金额时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_applyFeePer3关键字
	 * 参数applyFeePer作为输入的第2阶梯的费率 （均传入一个参数）
	 */
	public static void input_subscribe_applyFeePer3(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-applyFeePer-three")).sendKeys(applyFeePer);

			Log.info("输入第3阶梯的认购费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第3阶梯的认购费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_subscribe_dividePro3关键字
	 * 参数dividePro作为第3阶梯的百分比 （均传入一个参数）
	 */
	public static void input_subscribe_dividePro3(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.subscribe-dividePro-three")).sendKeys(dividePro);

			Log.info("输入第3阶梯收费的百分比");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第3阶梯收费的百分比时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save_subscribeFee关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_subscribeFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_subscribeFee")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的confirm_fee关键字
	 * 实现点击保存成功的确认操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void confirm_fee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.confirm_fee")).click();
			Log.info("点击保存成功的确认按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存成功的确认按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 申购费
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addapplyFee关键字 实现点击申购费按钮操作 （均传入一个参数）
	 */
	public static void is_addapplyFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addapplyFee")).click();

			Log.info("点击申购费按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击申购费按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}

	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_applyFee_applyFeePer1关键字
	 * 参数applyFeePer作为输入的第一阶梯的费率 （均传入一个参数）
	 */
	public static void input_applyFee_applyFeePer1(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.apply-applyFeePer")).sendKeys(applyFeePer);

			Log.info("输入第一阶梯的申购费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第一阶梯的申购费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_applyFee_dividePro1关键字 参数dividePro作为第一阶梯的百分比
	 * （均传入一个参数）
	 */
	public static void input_applyFee_dividePro1(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.apply-dividePro")).sendKeys(dividePro);

			Log.info("输入第一阶梯收费的百分比");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第一阶梯收费的百分比时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save-apply-1关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_applyFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_applyFee")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 管理费
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addmanageFee关键字 实现点击管理费按钮操作 （均传入一个参数）
	 */
	public static void is_addmanageFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addmanageFee")).click();

			Log.info("点击管理费按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击管理费按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_year关键字 参数applyFeePer作为一次性提取年份 （均传入一个参数）
	 */
	public static void input_year(String year) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_year")).sendKeys(year);

			Log.info("输入一次性提取年份");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入一次性提取年份时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFeeAfterDate关键字
	 * 参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_manageFeeAfterDate(String string) {
		ca.add(Calendar.DATE, 15);// 当前日期加15天
		date = ca.getTime();
		String date2 = dataFormat.format(date);
		
		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFeeAfterDate")).sendKeys(date2);

			Log.info("输入后续管理费首次提取时间");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入后续管理费首次提取时间时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_applyFeePer关键字
	 * 参数applyFeePer作为年化管理费率 （均传入一个参数）
	 */
	public static void input_manageFee_applyFeePer1(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_applyFeePer_first")).sendKeys(applyFeePer);

			Log.info("输入年化管理费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入年化管理费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_dividePro关键字 参数dividePro作为管理费的分成比例
	 * （均传入一个参数）
	 */
	public static void input_manageFee_dividePro1(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_dividePro_first")).sendKeys(dividePro);

			Log.info("输入管理费的分成比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入管理费的分成比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_applyFeeFrom_second关键字
	 * 参数applyFeeFrom作为第二期阶梯管理费资产规模 （均传入一个参数）
	 */
	public static void input_manageFee_applyFeeFrom_second(String applyFeeFrom) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_applyFeeFrom_second")).sendKeys(applyFeeFrom);

			Log.info("输入第二期阶梯管理费资产规模");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第二期阶梯管理费资产规模时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_applyFeePer2关键字
	 * 参数applyFeePer作为第二阶梯年化管理费率 （均传入一个参数）
	 */
	public static void input_manageFee_applyFeePer2(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_applyFeePer_second")).sendKeys(applyFeePer);

			Log.info("输入第二阶梯年化管理费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第二阶梯年化管理费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}
	/*
         * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_dividePro关键字
         * 参数dividePro作为第二阶梯管理费的分成比例（均传入一个参数）
         */
	public static void input_manageFee_dividePro2(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_dividePro_second")).sendKeys(dividePro);

			Log.info("输入第二阶梯管理费的分成比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第二阶梯管理费的分成比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_applyFeeFrom_second关键字
	 * 参数applyFeeFrom作为第三期阶梯管理费资产规模 （均传入一个参数）
	 */
	public static void input_manageFee_applyFeeFrom_three(String applyFeeFrom) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_applyFeeFrom_three")).sendKeys(applyFeeFrom);

			Log.info("输入第三期阶梯管理费资产规模");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第三期阶梯管理费资产规模时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_applyFeePer3关键字
	 * 参数applyFeePer作为第三阶梯年化管理费率 （均传入一个参数）
	 */
	public static void input_manageFee_applyFeePer3(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_applyFeePer_three")).sendKeys(applyFeePer);

			Log.info("输入第三阶梯年化管理费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第三阶梯年化管理费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
             * 此方法的名称对应Excel文件“关键字”列中的input_manageFee_dividePro关键字
             * 参数dividePro作为第三阶梯管理费的分成比例（均传入一个参数）
             */
	public static void input_manageFee_dividePro3(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_manageFee_dividePro_three")).sendKeys(dividePro);

			Log.info("输入第三阶梯管理费的分成比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第三阶梯管理费的分成比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的add_manageFee关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void add_manageFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.add_manageFee")).click();
			Log.info("点击添加阶梯管理费率按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击添加阶梯管理费率按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}


	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save_manageFee关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_manageFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_manageFee")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 业绩报酬
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addperformance关键字 实现点击业绩报酬按钮操作 （均传入一个参数）
	 */
	public static void is_addperformance(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addperformance")).click();

			Log.info("点击业绩报酬按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击业绩报酬按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_performance_applyFeePer关键字
	 * 参数applyFeePer作为计提比例 （均传入一个参数）
	 */
	public static void input_performance_applyFeePer(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_performance_applyFeePer")).sendKeys(applyFeePer);

			Log.info("输入计提比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入计提比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_performance_dividePro关键字
	 * 参数dividePro作为业绩报酬的分成比例 （均传入一个参数）
	 */
	public static void input_performance_dividePro(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_performance_dividePro")).sendKeys(dividePro);

			Log.info("输入业绩报酬的分成比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入业绩报酬的分成比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save_performance关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_performance(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_performance")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 赎回费
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addredemptionFee关键字 实现点击赎回费按钮操作， （均传入一个参数）
	 */
	public static void is_addredemptionFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addredemptionFee")).click();

			Log.info("点击赎回费按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击赎回费按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_redemptionFee_applyFeePer关键字
	 * 参数applyFeePer作为赎回费率 （均传入一个参数）
	 */
	public static void input_redemptionFee_applyFeePer(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_redemptionFee_applyFeePer"))
					.sendKeys(applyFeePer);

			Log.info("输入赎回费率");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入赎回费率时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_redemptionFee_dividePro关键字
	 * 参数dividePro作为赎回费的分成比例 （均传入一个参数）
	 */
	public static void input_redemptionFee_dividePro(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_redemptionFee_dividePro")).sendKeys(dividePro);

			Log.info("输入赎回费的分成比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入赎回费的分成比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save_redemptionFee关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_redemptionFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_redemptionFee")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 其他收入
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的is_addotherFee关键字 实现点击其他收入按钮操作 （均传入一个参数）
	 */
	public static void is_addotherFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.is_addotherFee")).click();
			Log.info("点击其他收入按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击其他收入按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_otherFee_applyFeePer关键字 参数applyFeePer作为奖励金额
	 * （均传入一个参数）
	 */
	public static void input_otherFee_applyFeePer(String applyFeePer) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_otherFee_applyFeePer")).sendKeys(applyFeePer);

			Log.info("输入奖励金额");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入奖励金额时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_otherFee_ps关键字 参数dividePro作为其他收入的备注
	 * （均传入一个参数）
	 */
	public static void input_otherFee_ps(String dividePro) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.input_otherFee_ps")).sendKeys(dividePro);

			Log.info("输入其他收入的备注");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入其他收入的备注时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的save_otherFee关键字
	 * 实现点击保存按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void save_otherFee(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.save_otherFee")).click();
			Log.info("点击保存按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击保存按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_back关键字
	 * 实现点击返回按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_back(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.click_back")).click();
			Log.info("点击返回按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击返回按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_submit关键字
	 * 实现点击提交审批按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_submit(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.click_submit")).click();
			Log.info("点击提交审批按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击提交审批按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_submit关键字
	 * 实现点击提交审批按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_Updata(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.click_update")).click();
			Log.info("点击更新费率按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击更新费率按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}
	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_incomeCalculationSource关键字
	 * 实现选择打包价按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_incomeCalculationSource(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.incomeCalculationSource")).click();
			Log.info("选择打包价按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择打包价按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_isInstallment2关键字
	 * 实现选择分期按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_isInstallment2(String string) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.isInstallment2")).click();
			Log.info("选择分期是按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("选择分期是按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioPro1关键字
	 * 实现输入第一期认缴比例，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioPro1(String InsRatioPro1) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioPro1")).sendKeys(InsRatioPro1);
			Log.info("输入第一期认缴比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第一期认缴比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioPro2关键字
	 * 实现选择分期按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioPro2(String InsRatioPro2) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioPro2")).sendKeys(InsRatioPro2);
			Log.info("输入第二期认缴比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第二期认缴比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioPro3关键字
	 * 实现选择分期按钮操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioPro3(String InsRatioPro3) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioPro3")).sendKeys(InsRatioPro3);
			Log.info("输入第三期认缴比例");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("输入第三期认缴比例时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的click_addInsRatio关键字
	 * 实现添加分期认缴比例，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void click_addInsRatio(String InsRatioPro3) {

		try {
			driver.findElement(objectMap.getLocator("ProductFee.addInsRatio")).click();
			Log.info("点击添加分期认缴比例按钮");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("点击添加分期认缴比例按钮时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioDate1关键字
	 * 实现添加不分期日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioDate1(String string) {

		ca.add(Calendar.DATE, 1);// 当前日期加1天
		date = ca.getTime();
		String date2 = dataFormat.format(date);
		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioDate1")).sendKeys(date2);
			Log.info("添加第一期认缴时间");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加第一期认缴时间时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioDate1关键字
	 * 实现添加不分期日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioDate2(String string) {

		ca.add(Calendar.DATE, 10);// 当前日期加10天
		date = ca.getTime();
		String date2 = dataFormat.format(date);
		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioDate2")).sendKeys(date2);
			Log.info("添加第二期认缴时间");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加第二期认缴时间时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * 此方法的名称对应Excel文件“关键字”列中的input_InsRatioDate1关键字
	 * 实现添加不分期日期操作，参数String本身并不会作为操作的输入值，设定一个无用的参数仅仅为了统一反射方法的调用方式 （均传入一个参数）
	 */
	public static void input_InsRatioDate3(String string) {

		ca.add(Calendar.DATE, 15);// 当前日期加15天
		date = ca.getTime();
		String date2 = dataFormat.format(date);
		try {
			driver.findElement(objectMap.getLocator("ProductFee.InsRatioDate3")).sendKeys(date2);
			Log.info("添加第三期认缴时间");

		} catch (Exception e) {
			TestProductfeeByExcel.testResult = false;
			Log.info("添加第三期认缴时间时出现异常，具体异常信息:" + e.getMessage());
			e.printStackTrace();
		}
	}


	/*此方法的名称对应Excel文件“关键字”列中的click_Approval关键字
   * 审批合同，客服点击审批流链接
   */
	public static void click_Approval(String string){
		try {
			driver.switchTo().frame("home");
			driver.findElement( objectMap.getLocator( "ApprovalContractpage.ApprovalProcess" ) ).click();
			Log.info("单击审批产品费率按钮");
			// 得到当前窗口的句柄
			String currentWindow = driver.getWindowHandle();
			// 得到所有窗口的句柄
			Set<String> handles = driver.getWindowHandles();

			for (String handle : handles) {
				System.out.println(handle);
			}

			Iterator<String> it = handles.iterator();
			while (it.hasNext()) {
				String handle = it.next();
				if (currentWindow.equals(handle))
					continue;
				// 切换到新的窗口
				driver = driver.switchTo().window(handle);
				secondWindow = handle;

			}

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("单击审批合同按钮时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/*此方法的名称对应Excel文件“关键字”列中的click_Btn_view关键字
   * 点击页面的“查看”按钮
   */
	public static void click_Btn_view(String string){
		try {

			driver.switchTo().frame("frameDetail");
			Thread.sleep(2000);
			driver.findElement( objectMap.getLocator( "ApprovalProductFee.btn_view" ) ).click();
			Log.info("单击查看按钮");

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("单击查看按钮时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/*此方法的名称对应Excel文件“关键字”列中的click_Uploadfile关键字
   * 审批合同，客服点击审批流的确定按钮
	 */
	public static void click_Uploadfile(String string){
		try {
			Thread.sleep(3000);
			// 得到当前窗口的句柄
			String currentWindow1 = driver.getWindowHandle();
			// 得到所有窗口的句柄
			Set<String> handles1 = driver.getWindowHandles();

			for (String handle : handles1) {
				System.out.println(handle);
			}
			String secondWindow1 = "";
			Iterator<String> it1 = handles1.iterator();
			while (it1.hasNext()) {
				String handle = it1.next();
				if (driver.switchTo().window(handle).getTitle().contains("业务附件关系表管理")) {

					// 切换到新的窗口
					driver = driver.switchTo().window(handle);
					secondWindow1 = handle;

					System.out.println("title1,url1 = " + driver.getTitle() + "," + driver.getCurrentUrl());
					System.out.println("secondWindow1:" + secondWindow1);
					break;
				}
			}


			System.out.println(driver.getWindowHandle()  + " " + secondWindow1);
			driver.switchTo().window(secondWindow1);

			WebElement ele = driver.findElement(By.tagName("label"));
			ele.click();
			Runtime.getRuntime().exec("D:\\workspace\\Erp-Automated-Test\\upload.exe"+ " " + "chrome" + " " + "D:\\图片\\打款凭条.jpg");
			Thread.sleep( 5000 );
			driver.switchTo().window(secondWindow1).close();

			Log.info("单击上传文件按钮");

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("单击上传文件按钮时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}



    /*此方法的名称对应Excel文件“关键字”列中的click_btnAgree关键字
   * 审批合同，客服点击审批流的确定按钮
   */
	public static void click_btnAgree(String string){
		try {
			driver.switchTo().window(secondWindow);

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

	/*此方法的名称对应Excel文件“关键字”列中的input_recGainFromSale关键字
  * 输入项目部建议提成费率
  */
	public static void input_recGainFromSale(String recGainFromSale){
		try {

			// 输入项目部建议提成费率
			driver.findElement( objectMap.getLocator( "ApprovalProductFee.recGainFromSale" ) ).sendKeys(recGainFromSale);
			Log.info("输入项目部建议提成费率");

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("输入项目部建议提成费率时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/*此方法的名称对应Excel文件“关键字”列中的input_gainFromSale关键字
  * 输入项目部建议提成费率
  */
	public static void input_gainFromSale(String gainFromSale){
		try {

			// 输入产品销售提成费率
			driver.findElement( objectMap.getLocator( "ApprovalProductFee.gainFromSale" ) ).sendKeys(gainFromSale);
			Log.info("输入产品销售提成费率");

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("输入产品销售提成费率时出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}

	/*此方法的名称对应Excel文件“关键字”列中的click_SaveFree关键字
* 点击输入提成费率录入的保存按钮
*/
	public static void click_SaveFree(String String){
		try {

			WebElement SaveBtn = SaveFree();

			if(SaveBtn != null)
			{
				SaveBtn.click();
			}


			Log.info("点击输入提成费率录入的保存按钮");

		} catch (Exception e) {
			TestContractByExcel.testResult = false;
			Log.info("点击输入提成费率录入的保存按钮时出现异常，具体异常信息:"+e.getMessage());
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
			TestProductfeeByExcel.testResult = false;
			Log.info("关闭浏览器出现异常，具体异常信息:"+e.getMessage());
			e.printStackTrace();
		}
	}

	private static WebElement findCurrentTabframe() {
		List<WebElement> eles = driver.findElements(By.tagName("iframe"));

		System.out.println(eles.size() + " ======");

		WebElement eleRet = null;

		for (WebElement ele : eles) {
			String src = ele.getAttribute("src");
			String id = ele.getAttribute("id");
			System.out.println("src: " + src + "id: " + id);
			//if (src != null && src.contains("privateEquityProductInfo"))
			if (src != null && src.contains("privateEquityProductInfo")) {
				eleRet = ele;
				break;
			}
		}

		return eleRet;
	}

	//查找pe/vc的URL地址信息
	private static WebElement findCurrentTabframePE() {
		List<WebElement> eles = driver.findElements(By.tagName("iframe"));

		System.out.println(eles.size() + " ======");

		WebElement eleRet = null;

		for (WebElement ele : eles) {
			String src = ele.getAttribute("src");
			String id = ele.getAttribute("id");
			System.out.println("src: " + src + "id: " + id);
			//if (src != null && src.contains("privateEquityProductInfo"))
			if (src != null && src.contains("PEVCProductInfo")) {
				eleRet = ele;
				break;
			}
		}

		return eleRet;
	}

	private static WebElement SaveFree() {

		//遍历提成费率录入弹出框，当找到录入项目部建议提成费率的时候，输入该费率
		List<WebElement> eles = driver.findElements( By.tagName( "div" ) );

		System.out.println( eles.size() + " ======" );

		WebElement eleRet = null;

		for (WebElement ele : eles) {
			String id = ele.getAttribute( "class" );

			if (id.startsWith( "l-dialog-btn-inner" ) && ele.getText().equals( "确认保存" )) {
				System.out.println( ele.getText() );
				ele.click();
				break;
			}
		}
		return eleRet;
	}

}
