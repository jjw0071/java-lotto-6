package lotto.ui;

import camp.nextstep.edu.missionutils.Console;
import lotto.Lotto;
import lotto.util.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleUI {
    private final Validator validator;

    public ConsoleUI(Validator validator) {
        this.validator = validator;
    }

    public int getPurchaseAmount() {
        int lottoPurchaseCost;
        do {
            lottoPurchaseCost = attemptToGetValidPurchaseAmount();
        } while (lottoPurchaseCost == -1);
        return lottoPurchaseCost/1000;
    }

    private int attemptToGetValidPurchaseAmount() {
        try {
            String input = promptForPurchaseAmount();
            return validator.validatePurchaseAmountInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    private String promptForPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return Console.readLine();
    }

    public List<Integer> getLottoNumbers() {
        List<Integer> lottoNumbers;
        do{
            lottoNumbers = attemptToGetValidLottoNumbers();
        }while(lottoNumbers==null);
        return lottoNumbers;
    }

    private List<Integer> attemptToGetValidLottoNumbers() {
        try {
            String input = promptForLottoNumbers();
            return validator.validateLottoNumbersInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String promptForLottoNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Console.readLine();
    }

    public int getBonusNumber(List<Integer> winningLottoNumbers) {
        int bonusNumber;
        do {
            bonusNumber = attemptToGetValidBonusNumber(winningLottoNumbers);
        } while (bonusNumber == -1);
        return bonusNumber;
    }

    private int attemptToGetValidBonusNumber(List<Integer> winningLottoNumbers) {
        try {
            String input = promptForBonusNumber();
            int bonusNumber =validator.validateBonusNumber(input);
            validator.compareWithLottoNumbers(winningLottoNumbers,bonusNumber);
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    private String promptForBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return Console.readLine();
    }


    public void displayLottoNumbers(List<Lotto> lottos) {
        System.out.println(lottos.size()+"개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(formatNumbersForDisplay(lotto.getNumbers()));
        }
    }

    private String formatNumbersForDisplay(List<Integer> numbers) {
        return "[" +numbers.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + "]";
    }
}
