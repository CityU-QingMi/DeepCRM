    public final synchronized void startup() {
        if (!this.isRunning()) {
            try {
                this.startupInternal();
                this.running = true;
            } catch (final Exception e) {
                logError("Could not perform database startup operations", e);
            }
        }
    }
