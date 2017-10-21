    public int getPollHeartbeatRetries() {

        int retries = POLL_RETRIES_DEFAULT;

        try {
            retries = Integer.getInteger(
                HsqlDatabaseProperties.system_lockfile_poll_retries_property,
                retries).intValue();
        } catch (Exception e) {}

        if (retries < 1) {
            retries = 1;
        }

        return retries;
    }
