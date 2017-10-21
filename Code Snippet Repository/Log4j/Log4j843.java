    @Override
    public void update(final String key, final LogEvent event) {
        final long now = System.currentTimeMillis();
        appendersUsage.put(key, now);
        if (future == null) {
            synchronized (this) {
                if (future == null) {
                    scheduleNext();
                }
            }
        }

    }
