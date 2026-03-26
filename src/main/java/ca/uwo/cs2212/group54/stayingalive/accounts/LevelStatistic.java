public class LevelStatistic {

    private LevelData levelData;
    int wordsPerMinute;
    int mistakes;
    int highscore;
    int attempts;
    Level_status status;

    public LevelStatistic(LevelData levelData) {
        this.levelData = levelData;
        mistakes = wordsPerMinute = highscore = attempts = 0;
        status = Level_status.UNLOCKED;
    }

    public void updateStats(int wordsPerMinute, int mistakes, int highscore, int attempts, Level_status status) {
        this.wordsPerMinute = wordsPerMinute;
        this.mistakes = mistakes;
        this.highscore = highscore;
        this.attempts = attempts;
        this.status = status;
    }

}