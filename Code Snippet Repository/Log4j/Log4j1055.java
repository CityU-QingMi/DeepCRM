    private boolean configureExistingStatusConsoleListener() {
        boolean configured = false;
        for (final StatusListener statusListener : this.logger.getListeners()) {
            if (statusListener instanceof StatusConsoleListener) {
                final StatusConsoleListener listener = (StatusConsoleListener) statusListener;
                listener.setLevel(this.status);
                this.logger.updateListenerLevel(this.status);
                if (this.verbosity == Verbosity.QUIET) {
                    listener.setFilters(this.verboseClasses);
                }
                configured = true;
            }
        }
        return configured;
    }
