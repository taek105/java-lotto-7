package lotto;

import lotto.controller.LottoMachine;

public class Application {
    public static void main(String[] args) {
        LottoMachine machine = new LottoMachine();

        machine.start();
    }
}