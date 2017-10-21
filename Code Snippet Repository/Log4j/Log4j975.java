    public void setMonitorInterval(final int intervalSeconds) {
        if (this instanceof Reconfigurable && intervalSeconds > 0) {
            final ConfigurationSource configSource = getConfigurationSource();
            if (configSource != null) {
                final File configFile = configSource.getFile();
                if (intervalSeconds > 0) {
                    getWatchManager().setIntervalSeconds(intervalSeconds);
                    if (configFile != null) {
                        final FileWatcher watcher = new ConfiguratonFileWatcher((Reconfigurable) this, listeners);
                        getWatchManager().watchFile(configFile, watcher);
                    }
                }
            }
        }
    }
