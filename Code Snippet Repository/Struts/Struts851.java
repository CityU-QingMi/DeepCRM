    public long getTime() {
        // Return how long time has passed since last check point
        long now = System.currentTimeMillis();
        long time = now - current;

        // Reset so that next time we get from this point
        current = now;

        return time;
    }
