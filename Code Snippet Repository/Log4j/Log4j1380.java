    private void formatWithoutThreadLocals(final long timestampMillis, final StringBuilder output) {
        CachedTime cached = cachedTime.get();
        if (timestampMillis != cached.timestampMillis) {
            final CachedTime newTime = new CachedTime(timestampMillis);
            if (cachedTime.compareAndSet(cached, newTime)) {
                cached = newTime;
            } else {
                cached = cachedTime.get();
            }
        }
        output.append(cached.formatted);
    }
