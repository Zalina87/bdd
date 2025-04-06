package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement code = $("[data-test-id=code] input");
    private final SelenideElement button = $("[data-test-id=action-verify]");

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        code.setValue(verificationCode.getCode());
        button.click();
        return new DashboardPage();
    }
}

