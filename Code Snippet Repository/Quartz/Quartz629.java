    void recordSample() {
        final long sample;
        if (resetOnSample) {
            sample = getAndReset();
        } else {
            sample = getValue();
        }

        final long now = System.currentTimeMillis();
        TimeStampedCounterValue timedSample = new TimeStampedCounterValue(now, sample);

        history.push(timedSample);
    }
