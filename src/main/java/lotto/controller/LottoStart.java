package lotto.controller;

import lotto.model.GenerateLottoNumber;
import lotto.model.LottoNumber;
import lotto.model.input.UserInput;
import lotto.model.validator.BonusNumValidator;
import lotto.model.validator.InputMoneyValidator;
import lotto.model.validator.WinNumValidator;
import lotto.view.LottoOutput;

import java.util.List;

public class LottoStart {
    UserInput userInput = new UserInput();
    public void start() {
        int money = inputMoney();
        List<LottoNumber> lottoNumbers = purchaseLotto(money);
        printGeneratedLotto(lottoNumbers);
        List<String> winNum = inputWinNum();
        String bonusNum = bonusNum(winNum);
    }
    private int inputMoney() {
        InputMoneyValidator validator = new InputMoneyValidator();
        String input = userInput.inputMoney();
        return validator.validateMoney(input);
    }
    private List<LottoNumber> purchaseLotto(int money) {
        GenerateLottoNumber generateLottoNumber = new GenerateLottoNumber(money);
        return generateLottoNumber.getGeneratedLotto();
    }

    private void printGeneratedLotto(List<LottoNumber> generatedLotto) {
        LottoOutput lottoOutput = new LottoOutput();
        lottoOutput.printLotto(generatedLotto);
    }
    private List<String> inputWinNum() {
        String winNum = userInput.inputWinNum();
        WinNumValidator winNumValidator = new WinNumValidator();
        return winNumValidator.validateWinNum(winNum.split(","));
    }
    private String bonusNum(List<String> winNum) {
        String bonusNum = userInput.inputBonusNum();
        BonusNumValidator bonusNumValidator = new BonusNumValidator(winNum);
        bonusNumValidator.validateBonusNum(bonusNum);
        return bonusNum;
    }

}
