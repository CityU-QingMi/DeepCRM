    private boolean closeConnection() {
        if (connection == null) {
            return true;
        }
        final Connection temp = connection;
        connection = null;
        try {
            temp.close();
            return true;
        } catch (final JMSException e) {
            StatusLogger.getLogger().debug(
                    "Caught exception closing JMS Connection: {} ({}); continuing JMS manager shutdown",
                    e.getLocalizedMessage(), temp, e);
            return false;
        }
    }
