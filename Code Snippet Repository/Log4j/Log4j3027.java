    @Override
    public void wrapExecution(final Runnable runnable) {
        this.setLoggerContext();

        try {
            runnable.run();
        } finally {
            this.clearLoggerContext();
        }
    }
