    protected void performInitialDelay(BackgroundProcess bp) throws InterruptedException {
        if (delay <= 0 || delaySleepInterval <= 0) {
            return;
        }

        int steps = delay / delaySleepInterval;
        LOG.debug("Delaying for {} millis. (using {} steps)", delay, steps);
        int step;
        for (step = 0; step < steps && !bp.isDone(); step++) {
            Thread.sleep(delaySleepInterval);
        }
        LOG.debug("Sleeping ended after {} steps and the background process is {}", step, (bp.isDone() ? " done" : " not done"));
    }
