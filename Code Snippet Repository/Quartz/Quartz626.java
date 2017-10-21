    public synchronized Counter createCounter(CounterConfig config) {
        if (shutdown) {
            throw new IllegalStateException("counter manager is shutdown");
        }
        if (config == null) {
            throw new NullPointerException("config cannot be null");
        }
        Counter counter = config.createCounter();
        if (counter instanceof SampledCounterImpl) {
            SampledCounterImpl sampledCounter = (SampledCounterImpl) counter;
            timer.schedule(sampledCounter.getTimerTask(), sampledCounter.getIntervalMillis(), sampledCounter.getIntervalMillis());
        }
        counters.add(counter);
        return counter;
    }
