    public synchronized void waitFor(boolean enforceSequence) {
        if (abort)
            throw new RuntimeException("Notifier side failed previously");
        if (notified) {
            if (enforceSequence)
                throw new RuntimeException(
                        "Request to wait on '" + key
                        + "', but this object has already been notified");
            return;
        }
        waiting = true;
        try {
            wait();
        } catch (InterruptedException ie) {
            throw new RuntimeException(
                    "Unexpected interrupted while waiting for '"
                    + key + "'", ie);
        } finally {
            waiting = false;
        }
        map.remove(this);
        if (!notified)
            throw new RuntimeException(
                    "Exiting waitFor() on '" + key
                    + "' even though not 'notified'");
    }
