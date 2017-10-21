    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        super.stop(timeout, timeUnit, false);
        final Map<String, Appender> map = configuration.getAppenders();
        for (final Map.Entry<String, AppenderControl> entry : appenders.entrySet()) {
            final Appender appender = entry.getValue().getAppender();
            if (!map.containsKey(appender.getName())) {
                if (appender instanceof LifeCycle2) {
                    ((LifeCycle2) appender).stop(timeout, timeUnit);
                } else {
                    appender.stop();
                }
            }
        }
        setStopped();
        return true;
    }
