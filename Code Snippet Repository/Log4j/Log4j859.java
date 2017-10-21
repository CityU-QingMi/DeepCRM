    public void deleteAppender(final String key) {
        LOGGER.debug("Deleting route with " + key + " key ");
        final AppenderControl control = appenders.remove(key);
        if (null != control) {
            LOGGER.debug("Stopping route with " + key + " key");
            control.getAppender().stop();
        } else {
            LOGGER.debug("Route with " + key + " key already deleted");
        }
    }
