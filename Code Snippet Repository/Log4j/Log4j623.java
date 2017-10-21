    @Override
    public void start() {
        if (getLayout() == null) {
            LOGGER.error("No layout set for the appender named [{}].", getName());
        }
        if (manager == null) {
            LOGGER.error("No OutputStreamManager set for the appender named [{}].", getName());
        }
        super.start();
    }
