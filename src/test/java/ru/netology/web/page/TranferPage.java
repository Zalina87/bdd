package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TranferPage {
    private final SelenideElement amount = $("[data-test-id=amount] input");
    private final SelenideElement from = $("[data-test-id=from] input");
    private final SelenideElement buttonTransfer = $("[data-test-id=action-transfer]");
    private final SelenideElement buttonCancel = $("[data-test-id=action-cancel]");

    public DashboardPage makeTransfer(int amount, String fromCard) {
        this.amount.setValue(String.valueOf(amount));
        from.setValue(fromCard);
        buttonTransfer.click();
        return new DashboardPage();
    }
}
