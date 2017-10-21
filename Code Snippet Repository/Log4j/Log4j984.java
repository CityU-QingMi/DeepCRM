    @Override
    public void setup() {
        final AbstractConfiguration targetConfiguration = configurations.get(0);
        staffChildConfiguration(targetConfiguration);
        final WatchManager watchManager = getWatchManager();
        final WatchManager targetWatchManager = targetConfiguration.getWatchManager();
        final FileWatcher fileWatcher = new ConfiguratonFileWatcher(this, listeners);
        if (targetWatchManager.getIntervalSeconds() > 0) {
            watchManager.setIntervalSeconds(targetWatchManager.getIntervalSeconds());
            final Map<File, FileWatcher> watchers = targetWatchManager.getWatchers();
            for (final Map.Entry<File, FileWatcher> entry : watchers.entrySet()) {
                if (entry.getValue() instanceof ConfiguratonFileWatcher) {
                    watchManager.watchFile(entry.getKey(), fileWatcher);
                }
            }
        }
        for (final AbstractConfiguration sourceConfiguration : configurations.subList(1, configurations.size())) {
            staffChildConfiguration(sourceConfiguration);
            final Node sourceRoot = sourceConfiguration.getRootNode();
            mergeStrategy.mergConfigurations(rootNode, sourceRoot, getPluginManager());
            if (LOGGER.isEnabled(Level.ALL)) {
                final StringBuilder sb = new StringBuilder();
                printNodes("", rootNode, sb);
                System.out.println(sb.toString());
            }
            final int monitorInterval = sourceConfiguration.getWatchManager().getIntervalSeconds();
            if (monitorInterval > 0) {
                final int currentInterval = watchManager.getIntervalSeconds();
                if (currentInterval <= 0 || monitorInterval < currentInterval) {
                    watchManager.setIntervalSeconds(monitorInterval);
                }
                final WatchManager sourceWatchManager = sourceConfiguration.getWatchManager();
                final Map<File, FileWatcher> watchers = sourceWatchManager.getWatchers();
                for (final Map.Entry<File, FileWatcher> entry : watchers.entrySet()) {
                    if (entry.getValue() instanceof ConfiguratonFileWatcher) {
                        watchManager.watchFile(entry.getKey(), fileWatcher);
                    }
                }
            }
        }
    }
