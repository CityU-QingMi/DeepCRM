    public synchronized void setLevel(final Level level) {
        if (level == getLevel()) {
            return;
        }
        Level actualLevel;
        if (level != null) {
            actualLevel = level;
        } else {
            final Logger parent = getParent();
            actualLevel = parent != null ? parent.getLevel() : privateConfig.loggerConfigLevel;
        }
        privateConfig = new PrivateConfig(privateConfig, actualLevel);
    }
