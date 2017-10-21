    public String getDatabaseName(int index, boolean asconfigured) {

        if (asconfigured) {
            return serverProperties.getProperty(ServerProperties.sc_key_dbname
                                                + "." + index);
        } else if (getState() == ServerConstants.SERVER_STATE_ONLINE) {
            return (dbAlias == null || index < 0 || index >= dbAlias.length)
                   ? null
                   : dbAlias[index];
        } else {
            return null;
        }
    }
