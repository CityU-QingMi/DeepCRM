    private void scheduleNextRollover(ZonedDateTime now)
    {
        _rollTask = new RollTask();
        // Get tomorrow's midnight based on Configured TimeZone
        ZonedDateTime midnight = toMidnight(now);

        // Schedule next rollover event to occur, based on local machine's Unix Epoch milliseconds
        long delay = midnight.toInstant().toEpochMilli() - now.toInstant().toEpochMilli();
        __rollover.schedule(_rollTask,delay);
    }
