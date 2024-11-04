package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import lotto.model.enums.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class ApplicationTest extends NsTest {
    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 로또_2등_기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("3000", "1,2,3,4,5,12", "6");
                    assertThat(output()).contains(
                            "3개를 구매했습니다.",
                            "[1, 2, 3, 4, 5, 6]",
                            "[1, 2, 3, 4, 5, 7]",
                            "[15, 16, 17, 18, 19, 20]",
                            "3개 일치 (5,000원) - 0개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 1개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 1050000.0%입니다."
                    );
                },
                List.of(1, 2, 3, 6, 5, 4),
                List.of(1, 2, 3 ,7, 4, 5),
                List.of(15, 16, 17, 18, 19, 20)
        );
    }

    @Test
    void 입력_반복_기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("3005", "3006", "8000", "1,2,3,4,5,6,7", "100,4,5", "1,2,3,4,5,6", "6", "7,7", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 구매금액_문자열_입력_예외_발생() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ErrorMessage.NATURAL_NUMBERS_ONLY.getMessage());
        });
    }

    @Test
    void 천원단위_예외_발생() {
        assertSimpleTest(() -> {
            runException("5005");
            assertThat(output()).contains(ErrorMessage.THOUSAND_UNIT_ONLY.getMessage());
        });
    }

    @Test
    void 당첨번호_문자열_입력_예외_발생() {
        assertSimpleTest(() -> {
            runException("5000", "45,taek,43,42,41,40");
            assertThat(output()).contains(ErrorMessage.NATURAL_NUMBERS_AND_COMMA_ONLY.getMessage());
        });
    }

    @Test
    void 당첨번호_공백_입력_예외_발생() {
        assertSimpleTest(() -> {
            runException("5000", " , , , , , ,");
            assertThat(output()).contains(ErrorMessage.NATURAL_NUMBERS_AND_COMMA_ONLY.getMessage());
        });
    }

    @Test
    void 보너스번호_문자열_입력_예외_발생() {
        assertSimpleTest(() -> {
            runException("5000", "1,2,3,4,5,6", "taek");
            assertThat(output()).contains(ErrorMessage.NATURAL_NUMBERS_ONLY.getMessage());
        });
    }

    @Test
    void 보너스번호_공백_입력_예외_발생() {
        assertSimpleTest(() -> {
            runException("5000", "1,2,3,4,5,6", " ");
            assertThat(output()).contains(ErrorMessage.NO_INPUT.getMessage());
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
