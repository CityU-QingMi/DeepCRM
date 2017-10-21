    @Deprecated
    public static JdbcDatabaseManager getJDBCDatabaseManager(final String name, final int bufferSize,
                                                             final ConnectionSource connectionSource,
                                                             final String tableName,
                                                             final ColumnConfig[] columnConfigs) {

        return getManager(name,
            new FactoryData(bufferSize, connectionSource, tableName, columnConfigs, new ColumnMapping[0]),
            getFactory());
    }
