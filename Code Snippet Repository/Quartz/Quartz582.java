    public int blockForAvailableThreads() {
        synchronized(nextRunnableLock) {

            while((availWorkers.size() < 1 || handoffPending) && !isShutdown) {
                try {
                    nextRunnableLock.wait(500);
                } catch (InterruptedException ignore) {
                }
            }

            return availWorkers.size();
        }
    }
