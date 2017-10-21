    private void waitForCompletion() {
        shutdownLock.lock();
        try {
            if (shutdown.compareAndSet(false, true)) {
                int retries = 0;
                // repeat while counter is non-zero
                while (!counter.compareAndSet(0, Integer.MIN_VALUE)) {

                    // counter was non-zero
                    if (counter.get() < 0) { // this should not happen
                        return; // but if it does, we are already done
                    }
                    // counter greater than zero, wait for afterLogEvent to decrease count
                    try {
                        noLogEvents.await(retries + 1, TimeUnit.SECONDS);
                    } catch (final InterruptedException ie) {
                        if (++retries > MAX_RETRIES) {
                            break;
                        }
                    }
                }
            }
        } finally {
            shutdownLock.unlock();
        }
    }
