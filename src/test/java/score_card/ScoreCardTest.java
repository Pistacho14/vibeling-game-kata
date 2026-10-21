package score_card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class ScoreCardTest {
    @Test
    void framesExtraRoll() {
        ScoreCard scoreCard = new ScoreCard("1212121212121212129/1");
        scoreCard.splitFrames();
        assertEquals(List.of(
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("9", "/", "1")), scoreCard.getFrames());
    }

    @Test
    void framesRolls() {
        ScoreCard scoreCard = new ScoreCard("1212121212121212129-");
        scoreCard.splitFrames();
        assertEquals(List.of(
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("1", "2"), List.of("1", "2"), List.of("1", "2"),
                List.of("9", "0")), scoreCard.getFrames());
    }

    @Test
    void numericalFramesRolls() {
        ScoreCard scoreCard = new ScoreCard("1212121212121212129-");
        scoreCard.splitFrames();
        assertEquals(repeatedNormalFrames(List.of(9, 0)), scoreCard.getCleanFrames());
    }

    @Test
    void numericalFramesRollsStrike() {
        ScoreCard scoreCard = new ScoreCard("X12X1212121212129-");
        scoreCard.splitFrames();
        assertEquals(List.of(
                List.of(10), List.of(1, 2), List.of(10), List.of(1, 2),
                List.of(1, 2), List.of(1, 2), List.of(1, 2), List.of(1, 2),
                List.of(1, 2), List.of(9, 0)), scoreCard.getCleanFrames());
    }

    @Test
    void numericalFramesRollsSpare() {
        ScoreCard scoreCard = new ScoreCard("1/121/1212121212129-");
        scoreCard.splitFrames();
        assertEquals(List.of(
                List.of(1, 9), List.of(1, 2), List.of(1, 9), List.of(1, 2),
                List.of(1, 2), List.of(1, 2), List.of(1, 2), List.of(1, 2),
                List.of(1, 2), List.of(9, 0)), scoreCard.getCleanFrames());
    }

    @Test
    void hittingPinsRegular() {
        assertScore("12345123451234512345", 60);
    }

    @Test
    void symbolZero() {
        assertScore("9-9-9-9-9-9-9-9-9-9-", 90);
        assertScore("9-3561368153258-7181", 82);
    }

    @Test
    void spareNotExtra() {
        assertScore("9-3/613/815/-/8-7/8-", 121);
    }

    @Test
    void strike() {
        assertScore("X9-9-9-9-9-9-9-9-9-", 100);
        assertScore("X9-X9-9-9-9-9-9-9-", 110);
    }

    @Test
    void twoStrikes() {
        assertScore("XX9-9-9-9-9-9-9-", 120);
    }

    @Test
    void threeStrikes() {
        assertScore("XXX9-9-9-9-9-9-", 141);
    }

    @Test
    void onePinInExtraRoll() {
        assertScore("9-3/613/815/-/8-7/8/8", 131);
        assertScore("5/5/5/5/5/5/5/5/5/5/5", 150);
    }

    @Test
    void twoStrikesInExtraRolls() {
        assertScore("9-9-9-9-9-9-9-9-9-XXX", 111);
    }

    @Test
    void oneStrikeInExtraRoll() {
        assertScore("8/549-XX5/53639/9/X", 149);
    }

    @Test
    void spareInExtraRoll() {
        assertScore("X5/X5/XX5/--5/X5/", 175);
    }

    @Test
    void tripleStrikeBeforeExtraRolls() {
        assertScore("XXXXXXXXXXXX", 300);
    }

    private static void assertScore(String pins, int expected) {
        assertEquals(expected, new ScoreCard(pins).scoreCalculator());
    }

    private static List<List<Integer>> repeatedNormalFrames(List<Integer> lastFrame) {
        return List.of(
                List.of(1, 2), List.of(1, 2), List.of(1, 2), List.of(1, 2),
                List.of(1, 2), List.of(1, 2), List.of(1, 2), List.of(1, 2),
                List.of(1, 2), lastFrame);
    }
}
