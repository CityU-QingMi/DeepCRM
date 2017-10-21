    public HsqlProperties getClientProperties() {

        if (clientProperties == null) {
            clientProperties = new HsqlProperties();

            clientProperties.setProperty(
                HsqlDatabaseProperties.jdbc_translate_tti_types,
                database.sqlTranslateTTI);
            clientProperties.setProperty(
                HsqlDatabaseProperties.sql_live_object,
                database.sqlLiveObject);
        }

        return clientProperties;
    }
