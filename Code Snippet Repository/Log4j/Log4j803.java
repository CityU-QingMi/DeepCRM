    public synchronized void rollover() {
        if (!hasOutputStream()) {
            return;
        }
        if (rollover(rolloverStrategy)) {
            try {
                size = 0;
                initialTime = System.currentTimeMillis();
                createFileAfterRollover();
            } catch (final IOException e) {
                logError("Failed to create file after rollover", e);
            }
        }
    }
