    public synchronized void shutdown(boolean killTimer) {
        if (shutdown) {
            return;
        }
        try {
            // shutdown the counters of this counterManager
            for (Counter counter : counters) {
                if (counter instanceof SampledCounter) {
                    ((SampledCounter) counter).shutdown();
                }
            }
            if(killTimer)
                timer.cancel();
        } finally {
            shutdown = true;
        }
    }
