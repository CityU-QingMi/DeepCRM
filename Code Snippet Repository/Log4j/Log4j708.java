    public final synchronized boolean shutdown() {
        boolean closed = true;
        this.flush();
        if (this.isRunning()) {
            try {
                closed &= this.shutdownInternal();
            } catch (final Exception e) {
                logWarn("Caught exception while performing database shutdown operations", e);
                closed = false;
            } finally {
                this.running = false;
            }
        }
        return closed;
    }
