    private boolean closeSession() {
        if (session == null) {
            return true;
        }
        final Session temp = session;
        session = null;
        try {
            temp.close();
            return true;
        } catch (final JMSException e) {
            StatusLogger.getLogger().debug(
                    "Caught exception closing JMS Session: {} ({}); continuing JMS manager shutdown",
                    e.getLocalizedMessage(), temp, e);
            return false;
        }
    }
