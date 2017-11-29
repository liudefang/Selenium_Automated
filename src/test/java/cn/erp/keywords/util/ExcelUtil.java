package cn.erp.keywords.util;
import cn.erp.keywords.configuration.Constants;
//import cn.erp.keywords.testScript.TestContractByExcel;
import cn.erp.keywords.testScript.TestProductfeeByExcel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by mike.liu on 2017/7/25.
 */

//本类主要实现扩展名为.xlsx的Excel文件操作
public class ExcelUtil {
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

    //设定要操作的Excel的文件路径和Excel文件中的sheet名称
    //在读/写Excel文件的时候，均需要先调用此方法，设定要操作的Excel文件路径和要操作的Sheet名称
    public static void setExcelFile(String Path) {
        FileInputStream ExcelFile;
        try {
            //实例化Excel文件的FileInputStream对象
            ExcelFile = new FileInputStream(Path);
            //实例化Excel文件的XSSFWorkbook对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);

        } catch (Exception e) {
            TestProductfeeByExcel.testResult = false;
            ////TestContractByExcel.testResult = false;
            System.out.println("Excel路径设定失败" );
            e.printStackTrace();

        }
    }
    //设定要操作的Excel的文件路径和Excel文件中的sheet名称
    public static void setExcelFile(String Path,String SheetName) {
        FileInputStream ExcelFile;
        try {
            //实例化Excel文件的FileInputStream对象
            ExcelFile = new FileInputStream( Path );
            //实例化Excel文件的XSSFWorkbook对象
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            /*实例化XSSFWorkbook对象，指定Excel文件中的Sheet名称，后续用于Sheet中行、
            *列和单元格的操作
             */
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
        } catch (Exception e) {
            TestProductfeeByExcel.testResult = false;
            //TestContractByExcel.testResult = false;
            System.out.println("Excel路径设定失败" );
            e.printStackTrace();
        }
    }
    //读取Excel文件指定单元格的函数，此函数只支持扩展名为.xlsx的Excel文件
    public static String getCellData(String SheetName, int RowNum, int ColNum)  {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        try {
            //通过函数参数指定单元格的行号和列号，获取指定的单元格对象
            Cell  = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            //如果单元格的内容为字符串类型，则使用getStringCellValue方法获取单元格的内容
            //如果单元格的内容为数字类型，则使用getNumericCellValue方法获取单元格的内容
            String CellData = Cell.getCellType() == XSSFCell.CELL_TYPE_STRING ? Cell.getStringCellValue() + "":String.valueOf( Math.round( Cell.getNumericCellValue() ));
            //函数返回指定单元格的字符串内容
            return CellData;

        } catch (Exception e) {
            TestProductfeeByExcel.testResult = false;
            //TestContractByExcel.testResult = false;
            e.printStackTrace();
            //读取遇到异常，则返回空字符串
            return "";
        }
    }
    //获取Excel文件最后一行的行号
    public static int getLastRowNum() {
        return ExcelWSheet.getLastRowNum();
    }
    //获取指定Sheet中的数据总行数
    public static int getRowCount(String SheetName) {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        int number = ExcelWSheet.getLastRowNum();
        return number;
    }
    //在Excel的指定Sheet中，获取第一次包含指定测试用例序号文字的行号
    public static int getFirstRowContainsTestCaseID (String sheetName,String testCaseName,int colNum)  {
        int i;
        try {


            ExcelWSheet = ExcelWBook.getSheet( sheetName );
            int rowCount = ExcelUtil.getRowCount( sheetName );
            for (i = 0; i < rowCount; i++) {
                //使用循环的方法遍历测试用例序号列的所有行，判断是否包含某个测试用例序号关键字
                if (ExcelUtil.getCellData( sheetName, i, colNum ).equalsIgnoreCase( testCaseName )) {
                    //如果包含，则退出for循环，并返回包含测试用例序号关键字的行号
                    break;
                }
            }
            return i;
        }catch (Exception e){
            TestProductfeeByExcel.testResult = false;
            //TestContractByExcel.testResult = false;
            return 0;
        }
    }
    //获取指定sheet中某个测试用例步骤的个数
    public static int getTestCaseLastStepRow(String SheetName, String testCaseID, int testCaseStartRowNumber) {
        try {

        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        /*从包含指定测试用例序号的第一行开始逐行遍历，直到某一行不出现指定测试用例序号
        * 此时的遍历次数就是此测试用例步骤的个数
         */
        for(int i = testCaseStartRowNumber; i<= ExcelUtil.getRowCount(SheetName)-1; i++ ) {
            if(! testCaseID.equals( ExcelUtil.getCellData( SheetName,i, Constants.Col_TestCaseID ) )) {
                int number = i;
                return number;
            }
        }
        int number = ExcelWSheet.getLastRowNum()+1;
        return number;
    } catch (Exception e) {
            TestProductfeeByExcel.testResult = false;
            //TestContractByExcel.testResult = false;
            return 0;
        }
    }
    //在Excel文件的执行单元格中写入数据,此函数只支持扩展名为.xlsx的Excel文件写入
    public static void setCellData(String SheetName,int RowNum,int ColNum,String Result) {
        ExcelWSheet = ExcelWBook.getSheet(SheetName);
        try {
            //获取Excel文件中的行对象
            Row = ExcelWSheet.getRow(RowNum);
            //如果单元格为空，则返回Null
            Cell = Row.getCell(ColNum,Row.RETURN_BLANK_AS_NULL);
            if(Cell == null) {
            //当单元格对象是Null的时候，则创建单元格
            //如果单元格为空，无法直接调用单元格对象的setCellValue方法设定单元格的值
                Cell = Row.createCell(ColNum);
             //创建单元格后可以调用单元格对象的setCellVa方法设定单元格的值
                Cell.setCellValue(Result);
            } else {
                //单元格中有内容，则可以直接调用单元格对象的setCellValue方法设定单元格的值
                Cell.setCellValue(Result);
            }
            //实例化写入Excel文件的文件输出流对象
            FileOutputStream fileOut = new FileOutputStream( Constants.Path_ExcelFile );
            //将内容写入Excel文件中
            ExcelWBook.write(fileOut);
            //调用flush方法强制刷新写入文件
            fileOut.flush();
            //关闭文件输出流对象
            fileOut.close();
        }catch (Exception e) {
            TestProductfeeByExcel.testResult = false;
            //TestContractByExcel.testResult = false;
            e.printStackTrace();
        }
    }


}
