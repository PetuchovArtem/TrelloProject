package tests;

import Pages.autorithation.BoardsPage;
import Pages.autorithation.InputLoginPage;
import Pages.autorithation.LoginPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static com.codeborne.selenide.Selenide.open;

public class TrelloTest {


    @BeforeAll
    public static void setUp() {

        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }

    @Test
    public void testAutorization() {
        LoginPage mainPage = open("https://trello.com/", LoginPage.class);

        BoardsPage inputLoginPage = mainPage.clickLoginButton()
                .setLoginField().clickProfillerButton();

        String userEmail = inputLoginPage.getUserEmail();
        Assertions.assertEquals("petuchov.artem@gmail.com", userEmail, "Phone name is not correct");

    }


}
