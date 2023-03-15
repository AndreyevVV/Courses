package ChatSocketServer;

public enum Flag {
    INSTANCE;

    private boolean shouldContinue;

    public boolean getShouldContinue() {
        return shouldContinue;
    }

    public void setShouldContinue(boolean shouldContinue) {
        this.shouldContinue = shouldContinue;
    }
}
