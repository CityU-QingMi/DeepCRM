    static WaitStrategy createWaitStrategy(final String propertyName, final long timeoutMillis) {
        final String strategy = PropertiesUtil.getProperties().getStringProperty(propertyName, "TIMEOUT");
        LOGGER.trace("property {}={}", propertyName, strategy);
        final String strategyUp = strategy.toUpperCase(Locale.ROOT); // TODO Refactor into Strings.toRootUpperCase(String)
        switch (strategyUp) { // TODO Define a DisruptorWaitStrategy enum?
        case "SLEEP":
            return new SleepingWaitStrategy();
        case "YIELD":
            return new YieldingWaitStrategy();
        case "BLOCK":
            return new BlockingWaitStrategy();
        case "BUSYSPIN":
            return new BusySpinWaitStrategy();
        case "TIMEOUT":
            return new TimeoutBlockingWaitStrategy(timeoutMillis, TimeUnit.MILLISECONDS);
        default:
            return new TimeoutBlockingWaitStrategy(timeoutMillis, TimeUnit.MILLISECONDS);
        }
    }
