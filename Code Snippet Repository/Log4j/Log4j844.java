    private void scheduleNext() {
        long updateTime = Long.MAX_VALUE;
        for (final Entry<String, Long> entry : appendersUsage.entrySet()) {
            if (entry.getValue() < updateTime) {
                updateTime = entry.getValue();
            }
        }

        if (updateTime < Long.MAX_VALUE) {
            final long interval = timeToLive - (System.currentTimeMillis() - updateTime);
            future = scheduler.schedule(this, interval, TimeUnit.MILLISECONDS);
        } else {
            // reset to initial state - in case of all appenders already purged
            future = scheduler.schedule(this, checkInterval, TimeUnit.MILLISECONDS);
        }
    }
