    public long getPollHeartbeatInterval() {

        int  retries  = getPollHeartbeatRetries();
        long interval = 10 + (HEARTBEAT_INTERVAL_PADDED / retries);

        try {
            interval = Long.getLong(POLL_INTERVAL_PROPERTY,
                                    interval).longValue();
        } catch (Exception e) {}

        if (interval <= 0) {
            interval = 10 + (HEARTBEAT_INTERVAL_PADDED / retries);
        }

        return interval;
    }
