    public void initialize() {
        if (!this.initialized) {
            if (this.status == Level.OFF) {
                this.initialized = true;
            } else {
                final boolean configured = configureExistingStatusConsoleListener();
                if (!configured) {
                    registerNewStatusConsoleListener();
                }
                migrateSavedLogMessages();
            }
        }
    }
