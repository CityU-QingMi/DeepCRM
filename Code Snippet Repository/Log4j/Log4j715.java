    @Deprecated
    public static <B extends Builder<B>> JdbcAppender createAppender(final String name, final String ignore,
                                                                     final Filter filter,
                                                                     final ConnectionSource connectionSource,
                                                                     final String bufferSize, final String tableName,
                                                                     final ColumnConfig[] columnConfigs) {
        Assert.requireNonEmpty(name, "Name cannot be empty");
        Objects.requireNonNull(connectionSource, "ConnectionSource cannot be null");
        Assert.requireNonEmpty(tableName, "Table name cannot be empty");
        Assert.requireNonEmpty(columnConfigs, "ColumnConfigs cannot be empty");

        final int bufferSizeInt = AbstractAppender.parseInt(bufferSize, 0);
        final boolean ignoreExceptions = Booleans.parseBoolean(ignore, true);

        return JdbcAppender.<B>newBuilder()
            .setBufferSize(bufferSizeInt)
            .setColumnConfigs(columnConfigs)
            .setConnectionSource(connectionSource)
            .setTableName(tableName)
            .withName(name)
            .withIgnoreExceptions(ignoreExceptions)
            .withFilter(filter)
            .build();
    }
