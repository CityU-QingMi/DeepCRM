    public static CassandraManager getManager(final String name, final SocketAddress[] contactPoints,
                                              final ColumnMapping[] columns, final boolean useTls,
                                              final String clusterName, final String keyspace, final String table,
                                              final String username, final String password,
                                              final boolean useClockForTimestampGenerator, final int bufferSize,
                                              final boolean batched, final BatchStatement.Type batchType) {
        return getManager(name,
            new FactoryData(contactPoints, columns, useTls, clusterName, keyspace, table, username, password,
                useClockForTimestampGenerator, bufferSize, batched, batchType), CassandraManagerFactory.INSTANCE);
    }
