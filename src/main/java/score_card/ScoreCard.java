package score_card;

import java.util.ArrayList;
import java.util.List;

public final class ScoreCard {
    private String pins;
    private final List<List<String>> frames = new ArrayList<>();
    private final List<List<Integer>> cleanFrames = new ArrayList<>();

    public ScoreCard(String pins) {
        this.pins = pins.replace("-", Pins.FOUL.symbol());
    }

    public String getPins() {
        return pins;
    }

    public List<List<String>> getFrames() {
        return frames;
    }

    public List<List<Integer>> getCleanFrames() {
        return cleanFrames;
    }

    public void setPins(String pins) {
        this.pins = pins;
    }

    public void setFrames(List<List<String>> frames) {
        this.frames.clear();
        this.frames.addAll(frames);
    }

    public void splitFrames() {
        frames.clear();
        cleanFrames.clear();

        int index = 0;
        for (int frame = 0; frame < 9; frame++) {
            String roll = pins.substring(index, index + 1);
            if (roll.equals(Pins.STRIKE.symbol())) {
                frames.add(List.of(roll));
                cleanFrames.add(List.of(Pins.TOTAL_PINS.numericValue()));
                index += Pins.ROLLS_STRIKE.numericValue();
            } else if (pins.substring(index + 1, index + 2).equals(Pins.SPAIR.symbol())) {
                frames.add(List.of(roll, Pins.SPAIR.symbol()));
                cleanFrames.add(List.of(Integer.parseInt(roll),
                        Pins.TOTAL_PINS.numericValue() - Integer.parseInt(roll)));
                index += Pins.NORMAL_FRAMES_ROLLS.numericValue();
            } else {
                frames.add(List.of(roll, pins.substring(index + 1, index + 2)));
                cleanFrames.add(List.of(Integer.parseInt(roll),
                        Integer.parseInt(pins.substring(index + 1, index + 2))));
                index += Pins.NORMAL_FRAMES_ROLLS.numericValue();
            }
        }

        List<String> lastFrame = new ArrayList<>(List.of(pins.substring(index).split("")));
        List<Integer> lastNumericalFrame = new ArrayList<>();
        for (int end = index; end < pins.length()
                && end < index + Pins.LAST_FRAME_ROLLS.numericValue(); end++) {
            String roll = pins.substring(end, end + 1);
            if (roll.equals(Pins.STRIKE.symbol())) {
                lastNumericalFrame.add(Pins.TOTAL_PINS.numericValue());
            } else if (roll.equals(Pins.SPAIR.symbol())) {
                lastNumericalFrame.add(Pins.TOTAL_PINS.numericValue()
                        - lastNumericalFrame.get(lastNumericalFrame.size() - 1));
            } else {
                lastNumericalFrame.add(Integer.parseInt(roll));
            }
        }

        frames.add(lastFrame);
        cleanFrames.add(lastNumericalFrame);
    }

    public int scoreCalculator() {
        splitFrames();

        int score = 0;
        int frameIndex = 0;
        for (List<String> frame : frames.subList(0, frames.size() - 1)) {
            if (isCommonRoll(frame)) {
                score += sum(cleanFrames.get(frameIndex));
            } else if (isSpareRoll(frame)) {
                score += 10 + cleanFrames.get(frameIndex + 1).get(0);
            } else if (cleanFrames.get(frameIndex + 1).size() >= 2) {
                score += 10 + sum(cleanFrames.get(frameIndex + 1).subList(0, 2));
            } else {
                score += 10 + cleanFrames.get(frameIndex + 1).get(0)
                        + cleanFrames.get(frameIndex + 2).get(0);
            }
            frameIndex++;
        }
        List<Integer> lastFrame = cleanFrames.get(cleanFrames.size() - 1);
        if (lastFrame.isEmpty()) {
            return score + cleanFrames.get(cleanFrames.size() - 2).get(0);
        }
        return score + sum(lastFrame);
    }

    private static int sum(List<Integer> rolls) {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    private static boolean isCommonRoll(List<String> frame) {
        return frame.size() == 2
                && Character.isDigit(frame.get(0).charAt(0))
                && Character.isDigit(frame.get(1).charAt(0));
    }

    private static boolean isSpareRoll(List<String> frame) {
        return frame.get(frame.size() - 1).equals(Pins.SPAIR.symbol());
    }

    @Override
    public String toString() {
        return pins;
    }
}
