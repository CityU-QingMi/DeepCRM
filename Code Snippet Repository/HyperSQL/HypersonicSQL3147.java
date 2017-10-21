    public String getDatabasePath(int index, boolean asconfigured) {

        if (asconfigured) {
            return serverProperties.getProperty(
                ServerProperties.sc_key_database + "." + index);
        } else if (getState() == ServerConstants.SERVER_STATE_ONLINE) {
            return (dbPath == null || index < 0 || index >= dbPath.length)
                   ? null
                   : dbPath[index];
        } else {
            return null;
        }
    }
