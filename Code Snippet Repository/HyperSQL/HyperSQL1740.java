    public void synch() {

        if (storeModified) {
            if (lobStore != null) {
                writeLock.lock();

                try {
                    try {
                        lobStore.synch();
                    } catch (Throwable t) {}

                    storeModified = false;
                } finally {
                    writeLock.unlock();
                }
            }
        }
    }
