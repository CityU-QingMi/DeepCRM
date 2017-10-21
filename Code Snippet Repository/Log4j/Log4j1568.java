    public void setIntervalSeconds(final int intervalSeconds) {
        if (!isStarted()) {
            if (this.intervalSeconds > 0 && intervalSeconds == 0) {
                scheduler.decrementScheduledItems();
            } else if (this.intervalSeconds == 0 && intervalSeconds > 0) {
                scheduler.incrementScheduledItems();
            }
            this.intervalSeconds = intervalSeconds;
        }
    }
