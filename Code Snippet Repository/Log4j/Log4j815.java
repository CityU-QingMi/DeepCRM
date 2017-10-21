    @Override
    public boolean isTriggeringEvent(final LogEvent event) {
        if (manager.getFileSize() == 0) {
            return false;
        }
        final long nowMillis = event.getTimeMillis();
        if (nowMillis >= nextRolloverMillis) {
            nextRolloverMillis = ThreadLocalRandom.current().nextLong(0, 1 + maxRandomDelayMillis)
                    + manager.getPatternProcessor().getNextTime(nowMillis, interval, modulate);
            return true;
        }
        return false;
    }
