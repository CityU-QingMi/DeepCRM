    @Override
    public void initialize(final RollingFileManager aManager) {
        this.manager = aManager;
        
        // LOG4J2-531: call getNextTime twice to force initialization of both prevFileTime and nextFileTime
        aManager.getPatternProcessor().getNextTime(aManager.getFileTime(), interval, modulate);
        
        nextRolloverMillis = ThreadLocalRandom.current().nextLong(0, 1 + maxRandomDelayMillis)
                + aManager.getPatternProcessor().getNextTime(aManager.getFileTime(), interval, modulate);
    }
