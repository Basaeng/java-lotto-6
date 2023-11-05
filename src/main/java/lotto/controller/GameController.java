package lotto.controller;

import lotto.domain.*;
import lotto.domain.enums.LottoPrize;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class GameController {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    public void init() {
        PurchaseAmount purchaseAmount = getPurchaseAmount();
        LottoCount lottoCount = LottoCount.from(purchaseAmount);
        List<Lotto> autoLottoTickets = Lotto.getAutoLottoTickets(lottoCount);
        outputView.displayLottoTickets(lottoCount, autoLottoTickets);

        Lotto winningLotto = getWinningLotto();
        BonusNumber bonusNumber = getBonusNumber(winningLotto);
        List<LottoPrize> allLottoPrizes = winningLotto.getAllLottoPrizes(autoLottoTickets, bonusNumber);

        for (LottoPrize allLottoPrize : allLottoPrizes) {
            System.out.println(allLottoPrize);
        }
    }

    public PurchaseAmount getPurchaseAmount() {
        while (true) {
            try {
                return PurchaseAmount.from(inputView.readPurchaseAmount());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public Lotto getWinningLotto() {
        while (true) {
            try {
                return Lotto.makeWinningNumbers(inputView.readWinningNumbers());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public BonusNumber getBonusNumber(Lotto winningLotto) {
        while (true) {
            try {
                WinningNumbers bonusNumber = WinningNumbers.from(inputView.readBonusNumber());
                return new BonusNumber(bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }
}
