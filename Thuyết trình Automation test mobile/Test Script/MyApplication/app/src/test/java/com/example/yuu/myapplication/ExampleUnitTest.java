package com.example.yuu.myapplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.SearchContext;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


import static java.lang.Thread.*;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    AndroidDriver driver;
    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setCapability("appium-version", "1.4.16.1");
        // cau hinh ten thiet bi
        capabilities.setCapability("deviceName", "RQ30069FZB");

        // cau hinh browser name, o day la android
        capabilities.setCapability("browserName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.0.0");// Bạn phải điền lại version của máy Android bạn đang dùng, máy mình là Android 5.0.1
        //capabilities.setCapability("deviceName", "RQ30069FZB"); // 0217da38 là deviceName mình đã lấy lúc trước

        //capabilities.setCapability("app", "/Users/Yuu/Desktop/TestAppium.apk"); // Mục đích của đoạn code này là tìm đường dẫn đến file apk của bạn, ở đây file cài của mình  là "flipkart.apk"
        capabilities.setCapability("appPackage","vn.tiki.app.tikiandroid"); //Bạn điền Package name của app đã lấy được ở trên vào đây
        capabilities.setCapability("appActivity", "vn.tiki.app.tikiandroid.ui.SplashActivity"); //Bạn điền Activity name của app đã lấy được ở trên vào đây
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); //Các bạn điền server address và port đã note lại ở bước trước

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //Là dòng lệnh để set timeout
    }
    //Kiểm Tra Họ Tên Rỗng
    @Test
    public void TestCase1() throws MalformedURLException {
        driver.findElementById("vn.tiki.app.tikiandroid:id/btnSignUp").click();
        //vn.tiki.app.tikiandroid:id/etName

        //vn.tiki.app.tikiandroid:id/etEmail
        driver.findElementById("vn.tiki.app.tikiandroid:id/etEmail").sendKeys("as.thienvuong@gmail.com");

        //vn.tiki.app.tikiandroid:id/etPassword
        driver.findElementById("vn.tiki.app.tikiandroid:id/etPassword").sendKeys("hung123456");

        //vn.tiki.app.tikiandroid:id/edBirthday
        //driver.findElementById("vn.tiki.app.tikiandroid:id/edBirthday").sendKeys("30-11-1994");

        driver.findElementById("vn.tiki.app.tikiandroid:id/btEditBirthday").click();
        //vn.tiki.app.tikiandroid:id/btFinish
        driver.findElementById("vn.tiki.app.tikiandroid:id/btFinish").click();

        //vn.tiki.app.tikiandroid:id/rbMale
        driver.findElementById("vn.tiki.app.tikiandroid:id/rbMale").click();

        //vn.tiki.app.tikiandroid:id/btRegister
        driver.findElementById("vn.tiki.app.tikiandroid:id/btRegister").click();

        //vn.tiki.app.tikiandroid:id/textinput_error
        String input = driver.findElementById("vn.tiki.app.tikiandroid:id/textinput_error").getText();
        assertEquals(input,"Xin vui lòng nhập vào họ tên");
        //vn.tiki.app.tikiandroid:id/textinput_error
        //vn.tiki.app.tikiandroid:id/textinput_error
        /*
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
    //Kiểm Tra Email/SĐT Rổng
    @Test
    public void TestCase2() throws MalformedURLException {
        driver.findElementById("vn.tiki.app.tikiandroid:id/btnSignUp").click();
        //vn.tiki.app.tikiandroid:id/etName
        driver.findElementById("vn.tiki.app.tikiandroid:id/etName").sendKeys("Hung");

        //vn.tiki.app.tikiandroid:id/etEmail

        //vn.tiki.app.tikiandroid:id/etPassword
        driver.findElementById("vn.tiki.app.tikiandroid:id/etPassword").sendKeys("hung123456");

        //vn.tiki.app.tikiandroid:id/edBirthday
        //driver.findElementById("vn.tiki.app.tikiandroid:id/edBirthday").sendKeys("30-11-1994");

        driver.findElementById("vn.tiki.app.tikiandroid:id/btEditBirthday").click();
        //vn.tiki.app.tikiandroid:id/btFinish
        driver.findElementById("vn.tiki.app.tikiandroid:id/btFinish").click();

        //vn.tiki.app.tikiandroid:id/rbMale
        driver.findElementById("vn.tiki.app.tikiandroid:id/rbMale").click();

        //vn.tiki.app.tikiandroid:id/btRegister
        driver.findElementById("vn.tiki.app.tikiandroid:id/btRegister").click();

        //vn.tiki.app.tikiandroid:id/textinput_error
        String input = driver.findElementById("vn.tiki.app.tikiandroid:id/textinput_error").getText();
        assertEquals(input,"Email");
        //vn.tiki.app.tikiandroid:id/textinput_error
        //vn.tiki.app.tikiandroid:id/textinput_error
        /*
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }
    //Kiểm Tra Email Không Hợp Quy Định
    @Test
    public void TestCase3() throws MalformedURLException {
        driver.findElementById("vn.tiki.app.tikiandroid:id/btnSignUp").click();
        //vn.tiki.app.tikiandroid:id/etName
        driver.findElementById("vn.tiki.app.tikiandroid:id/etName").sendKeys("Hung");

        //vn.tiki.app.tikiandroid:id/etEmail
        driver.findElementById("vn.tiki.app.tikiandroid:id/etEmail").sendKeys("abcd");

        //vn.tiki.app.tikiandroid:id/etPassword
        driver.findElementById("vn.tiki.app.tikiandroid:id/etPassword").sendKeys("hung123456");

        //vn.tiki.app.tikiandroid:id/edBirthday
        //driver.findElementById("vn.tiki.app.tikiandroid:id/edBirthday").sendKeys("30-11-1994");

        driver.findElementById("vn.tiki.app.tikiandroid:id/btEditBirthday").click();
        //vn.tiki.app.tikiandroid:id/btFinish
        driver.findElementById("vn.tiki.app.tikiandroid:id/btFinish").click();

        //vn.tiki.app.tikiandroid:id/rbMale
        driver.findElementById("vn.tiki.app.tikiandroid:id/rbMale").click();

        //vn.tiki.app.tikiandroid:id/btRegister
        driver.findElementById("vn.tiki.app.tikiandroid:id/btRegister").click();

        //vn.tiki.app.tikiandroid:id/textinput_error
        String input = driver.findElementById("vn.tiki.app.tikiandroid:id/textinput_error").getText();
        assertEquals(input,"Email/Số điện thoại không hợp lệ.");
        //vn.tiki.app.tikiandroid:id/textinput_error
        //vn.tiki.app.tikiandroid:id/textinput_error
        //try {
        //sleep(5000);
        //} catch (InterruptedException e) {
        //e.printStackTrace();
        //}
    }
    /*
    //Kiểm Tra SĐT đã đăng ký rồi
    @Test
    public void TestCase3() throws MalformedURLException {
        driver.findElementById("vn.tiki.app.tikiandroid:id/btnSignUp").click();
        //vn.tiki.app.tikiandroid:id/etName
        driver.findElementById("vn.tiki.app.tikiandroid:id/etName").sendKeys("Hung");

        //vn.tiki.app.tikiandroid:id/etEmail
        driver.findElementById("vn.tiki.app.tikiandroid:id/etEmail").sendKeys("0945356532");

        //vn.tiki.app.tikiandroid:id/etPassword
        driver.findElementById("vn.tiki.app.tikiandroid:id/etPassword").sendKeys("hung123456");

        //vn.tiki.app.tikiandroid:id/edBirthday
        //driver.findElementById("vn.tiki.app.tikiandroid:id/edBirthday").sendKeys("30-11-1994");

        driver.findElementById("vn.tiki.app.tikiandroid:id/btEditBirthday").click();
        //vn.tiki.app.tikiandroid:id/btFinish
        driver.findElementById("vn.tiki.app.tikiandroid:id/btFinish").click();

        //vn.tiki.app.tikiandroid:id/rbMale
        driver.findElementById("vn.tiki.app.tikiandroid:id/rbMale").click();

        //vn.tiki.app.tikiandroid:id/btRegister
        driver.findElementById("vn.tiki.app.tikiandroid:id/btRegister").click();

        //vn.tiki.app.tikiandroid:id/textinput_error
        //Alert alert = driver.switchTo().alert();
        //String input  = alert.getText();
        //System.out.print(input);
        String a = driver.switchTo().alert().getText();
        System.out.print(a);
        //driver.SwitchTo().alert().accept();
        //assertEquals(input,"Email này đã được sử dụng");
        //vn.tiki.app.tikiandroid:id/textinput_error
        //vn.tiki.app.tikiandroid:id/textinput_error
        //try {
            //sleep(5000);
        //} catch (InterruptedException e) {
            //e.printStackTrace();
        //}
    }*/
    @After
    public void A()
    {
        driver.quit();
    }

}