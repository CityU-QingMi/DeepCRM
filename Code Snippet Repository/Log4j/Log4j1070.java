    private Result filter(final Level level) {
        if (this.level.isMoreSpecificThan(level)) {
            LogDelay delay = history.poll();
            while (delay != null) {
                available.add(delay);
                delay = history.poll();
            }
            delay = available.poll();
            if (delay != null) {
                delay.setDelay(burstInterval);
                history.add(delay);
                return onMatch;
            }
            return onMismatch;
        }
        return onMatch;

    }
