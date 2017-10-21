    private void signalCompletionIfShutdown() {
        final Lock lock = shutdownLock;
        lock.lock();
        try {
            if (shutdown.get()) {
                noLogEvents.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
