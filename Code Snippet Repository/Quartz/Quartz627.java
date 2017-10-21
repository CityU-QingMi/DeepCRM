    public SampledCounterConfig(int intervalSecs, int historySize, boolean isResetOnSample, long initialValue) {
        super(initialValue);
        if (intervalSecs < 1) {
            throw new IllegalArgumentException("Interval (" + intervalSecs + ") must be greater than or equal to 1");
        }
        if (historySize < 1) {
            throw new IllegalArgumentException("History size (" + historySize + ") must be greater than or equal to 1");
        }

        this.intervalSecs = intervalSecs;
        this.historySize = historySize;
        this.isReset = isResetOnSample;
    }
