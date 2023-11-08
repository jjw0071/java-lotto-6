package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LottoService {
    private static final int LOTTO_MINIMUM_NUMBER = 1;
    private static final int LOTTO_MAXIMUM_NUMBER = 45;
    private static final int LOTTO_NUMBERS_COUNT = 6;

    public List<Lotto> purchaseLotto(int purchaseAmount){
        List<Lotto> purchasedLotto = new ArrayList<>();
        for(int i = 0 ; i < purchaseAmount;i++){
            List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(
                    LOTTO_MINIMUM_NUMBER,
                    LOTTO_MAXIMUM_NUMBER,
                    LOTTO_NUMBERS_COUNT
            );
            Lotto generatedLotto = new Lotto(lottoNumbers);
            purchasedLotto.add(generatedLotto);
        }
        return purchasedLotto;
    }

    public List<Lotto> purchaseLottos(int purchaseAmount) {
        List<Lotto> purchasedLottos = new ArrayList<>();
        while (purchasedLottos.size() < purchaseAmount) {
            Lotto generatedlotto = generateNonDuplicateLotto(purchasedLottos);
            purchasedLottos.add(generatedlotto);
        }
        return purchasedLottos;
    }

    private Lotto generateNonDuplicateLotto(List<Lotto> existingLottos) {
        Lotto generatedLotto;
        do {
            generatedLotto = new Lotto(generateLottoNumbers());
        } while (isDuplicate(existingLottos, generatedLotto));
        return generatedLotto;
    }

    private boolean isDuplicate(List<Lotto> existingLottos, Lotto newLotto) {
        for (Lotto lotto : existingLottos) {
            if (new HashSet<>(lotto.getNumbers()).containsAll(newLotto.getNumbers())) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> generateLottoNumbers() {
        return Randoms.pickUniqueNumbersInRange(
                LOTTO_MINIMUM_NUMBER,
                LOTTO_MAXIMUM_NUMBER,
                LOTTO_NUMBERS_COUNT
        );
    }
}
