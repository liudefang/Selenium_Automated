package cn.erp.keywords.util;
import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by mike.liu on 2017/7/25.
 */
public class ObjectMap {

    Properties properties;
    public ObjectMap(String propFile) {
        properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(propFile);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            System.out.println("读取对象文件出错");
            e.printStackTrace();
        }
    }
    public By getLocator(String ElementNameInpropFile) throws Exception {
        //根据变量ElementNameInpropFile,从属性配置文件中读取对应的配置对象
        String locator = properties.getProperty( ElementNameInpropFile );
        //将配置对象中的定位类型存储到locatorType变量中，将定位表达式的值存储到//locatorValue变量中
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];
        locatorValue = new String( locatorValue.getBytes("ISO-8859-1"),"UTF-8" );
        //输出locatorType和locatorValue变量值，验证赋值是否正确
        System.out.println("获取的定位类型：" + locatorType + "\t 获取的定位表达式" + locatorValue);
        //根据locatorType变量值的内容判断返回和定位方式的by对象
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if (locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if ((locatorType.toLowerCase().equals( "classname" )) || (locatorType.toLowerCase().equals("class")))
            return By.className( locatorValue );
        else if ((locatorType.toLowerCase().equals( "tagname" )) ||  (locatorType.toLowerCase().equals( "tag" )))
            return By.className( locatorValue );
        else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals( "link" )))
            return By.linkText( locatorValue );
        else if (locatorType.toLowerCase().equals( "partiallinktext" ))
            return By.partialLinkText( locatorValue );
        else if ((locatorType.toLowerCase().equals( "cssselector" )) || (locatorType.toLowerCase().equals( "css" )))
            return By.cssSelector( locatorValue );
        else if (locatorType.toLowerCase().equals( "xpath" ))
            return By.xpath( locatorValue );
        else throw new Exception( "输入的locator type未在程序中定义：" + locatorType );
    }
}
