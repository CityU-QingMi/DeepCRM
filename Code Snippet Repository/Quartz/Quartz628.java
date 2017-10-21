    public SampledCounterImpl(SampledCounterConfig config) {
        super(config.getInitialValue());

        this.intervalMillis = config.getIntervalSecs() * MILLIS_PER_SEC;
        this.history = new CircularLossyQueue<TimeStampedCounterValue>(config.getHistorySize());
        this.resetOnSample = config.isResetOnSample();

        this.samplerTask = new TimerTask() {
            @Override
            public void run() {
                recordSample();
            }
        };

        recordSample();
    }
