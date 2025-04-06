package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.TranferPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void openLoginPage() {
        open("http://localhost:9999/");
    }

    @Test
    void ShouldTransferMoneyBetweenCards() {
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        DashboardPage dashboardPage = verificationPage.validVerify(verificationCode);

        DataHelper.CardInfo firstCardInfo = DataHelper.getFirstCardInfo();
        int initBalanceFirst = dashboardPage.getCardBalance(firstCardInfo);
        DataHelper.CardInfo secondCardInfo = DataHelper.getSecondCardInfo();
        int initBalanceSecond = dashboardPage.getCardBalance(secondCardInfo);

        int transferAmount = 5000;
        TranferPage tranferPage = dashboardPage.selectCardForTransfer(firstCardInfo);
        dashboardPage = tranferPage.makeTransfer(transferAmount, secondCardInfo.getNumber());

        int newBalanceFirst = dashboardPage.getCardBalance(firstCardInfo);
        int newBalanceSecond = dashboardPage.getCardBalance(secondCardInfo);

        assertEquals(initBalanceFirst + transferAmount, newBalanceFirst);
        assertEquals(initBalanceSecond - transferAmount, newBalanceSecond);
    }
}
