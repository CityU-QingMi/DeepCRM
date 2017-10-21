    public static synchronized void init() throws WebloggerException {
        if (theInstance != null) {
            LOGGER.warn("Ignoring duplicate initialization of PingQueueProcessor!");
            return;
        }
        theInstance = new PingQueueProcessor();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ping queue processor initialized.");
        }
    }
