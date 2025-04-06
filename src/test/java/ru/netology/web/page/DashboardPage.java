package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private final ElementsCollection cards = $$(".list__item");

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        SelenideElement card = $("[data-test-id='" + cardInfo.getTestId() + "']");
        String text = card.getText();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        String strStart = "баланс: ";
        int start = text.indexOf(strStart);
        int end = text.indexOf(" р.");
        String value = text.substring(start + strStart.length(), end);
        return Integer.parseInt(value);
    }

    public TranferPage selectCardForTransfer(DataHelper.CardInfo cardInfo) {
        SelenideElement card = $("[data-test-id='" + cardInfo.getTestId() + "']");
        card.$("[data-test-id=action-deposit]").click();
        return new TranferPage();
    }
}
