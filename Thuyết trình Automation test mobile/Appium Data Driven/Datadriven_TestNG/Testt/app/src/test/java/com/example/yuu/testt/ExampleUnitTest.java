package com.example.yuu.testt;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
//thư viện testng
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import jxl.Sheet;
import jxl.Workbook;
import jxl.Cell;
import jxl.read.biff.BiffException;

import static org.testng.Assert.assertEquals;

public class ExampleUnitTest {
    AndroidDriver driver;
    By btnDangky = By.id("vn.tiki.app.tikiandroid:id/btnSignUp");

    By textviewHoten = By.id("vn.tiki.app.tikiandroid:id/etName");

    By textviewEmailphone = By.id("vn.tiki.app.tikiandroid:id/etEmail");

    By textviewMatkhau = By.id("vn.tiki.app.tikiandroid:id/etPassword");

    By textviewNgaysinh = By.id("vn.tiki.app.tikiandroid:id/edBirthday");

    By btnNgaysinh = By.id("vn.tiki.app.tikiandroid:id/btEditBirthday");

    By btnChonngaysinh = By.id("vn.tiki.app.tikiandroid:id/btFinish");

    By rdNam = By.id("vn.tiki.app.tikiandroid:id/rbMale");

    By rdNu = By.id("vn.tiki.app.tikiandroid:id/rbFemale");

    By btnHoantatdangky = By.id("vn.tiki.app.tikiandroid:id/btRegister");

    By textviewLoi = By.id("vn.tiki.app.tikiandroid:id/textinput_error");

    // ta có các beforemethod/beforeclass/beforetest tương tự với before của junit
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("appium-version", "1.4.16.1");
        // Cấu hình tên thiết bị
        capabilities.setCapability("deviceName", "BH9021A95L");
        // Cấu hình browser name, ở đây là android
        capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformName", "Android");
        // Version của máy Android đang sử dụng
        capabilities.setCapability("platformVersion", "8.0.0");
        // deviceName thiết bị
        capabilities.setCapability("deviceName", "BH9021A95L");
        // Tìm đường dẫn đến file apk của bạn, ở đây file cài của mình  là "flipkart.apk"
        //capabilities.setCapability("app", "C:/Users/Yuu/Desktop/Tiki.apk");
        // Bạn điền Package name của app lấy được
        capabilities.setCapability("appPackage", "vn.tiki.app.tikiandroid");
        // Activity name của app lấy được
        //vn.tiki.app.tikiandroid.ui.auth.view.AuthActivity
        //vn.tiki.app.tikiandroid.ui.SplashActivity
        capabilities.setCapability("appActivity", "vn.tiki.app.tikiandroid.ui.SplashActivity");
        // Server address và port Appium Server
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //Là dòng lệnh để set timeout
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
    }

    // đây là 1 testcase, để chạy nhiều trường hợp ta cần phải viết nhiều testcase như vầy để test
    // nhưng nếu làm như vậy sẽ tốn công sức và nhiều thời gian để viết
    // chính vì vậy ta cần một cách load dữ liệu động và chạy để tối ưu công việc
    @Test(dataProvider = "dp") //cách gọi mảng object vừa được load từ file, (dataprovider được testng hỗ trợ)
    public void TestCase(String fn, String un, String pw, String type, String out)
    //String fn, String un, String pw, String type, String out là các dữ liệu trong excel lấy ra
    {
        System.out.print("test: " + fn + " - " + un + " - " + pw + " - " + type + " - " + out + " - " );
        driver.findElement(btnDangky).click();
        //các trường cần nhập
        driver.findElement(textviewHoten).sendKeys(fn);
        driver.findElement(textviewEmailphone).sendKeys(un);
        driver.findElement(textviewMatkhau).sendKeys(pw);
        driver.findElement(btnNgaysinh).click();
        driver.findElement(btnChonngaysinh).click();
        driver.findElement(rdNam).click();
        driver.findElement(btnHoantatdangky).click();
        //kiểm tra loại testcase
        if (type.contentEquals("1")) {
            //kiểm tra kết quả trả về sau khi test
            String input = driver.findElement(textviewLoi).getText();
            assertEquals(input, out);
        } else {
            Assert.assertTrue(true);
        }
    }

    // tương tự với after
    @AfterMethod
    public void EndTest() {
        driver.quit();
    }

    // đây là nơi gọi data lên để dùng cho việc test
    // @dataprovider được testng hỗ trợ
    @DataProvider(name = "dp")// data sử dụng test
    public Object[][] createData() throws IOException {
        System.out.println("start");
        //gọi hàm get data từ file excel
        // ép mảng string thành mảng object
        Object[][] ar = getData("C:\\Users\\Akiyoshi\\Desktop\\TestData.xls");
        //System.out.print(ar[0][0] + "-" + ar[0][1]);
        //mảng object này được sử dũng trong test case
        return ar;
    }

    // hàm get data từ excel thành mảng 2 chiều string đơn giản
    public String[][] getData(String path) throws IOException {
        String[][] tb = new String[6][5];

        Workbook wb = null;
        try {
            wb = Workbook.getWorkbook(new File(path));
            System.out.println(wb.getSheets().length);
            Sheet sheet = wb.getSheet(0);
            System.out.println("get Data");
            for(int i=0; i<6; i++)
            {
                for (int j=0; j<5;j++){
                    System.out.println( i + "-" + j);
                    tb[i][j] =  sheet.getCell(j, i).getContents();
                }

                System.out.println(tb[i][0] + " - " + tb[i][1] + " - " + tb[i][2] + " - " + tb[i][3] + " - " + tb[i][4]);
            }
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {
            wb.close();
        }
        // return mảng string
        return tb;
    }
}