    private DataSource createMockDataSource() {
        try {
            final DataSource dataSource = mock(DataSource.class);
            given(dataSource.getConnection()).willAnswer(new Answer<Connection>() {
                @Override
                public Connection answer(final InvocationOnMock invocation) throws Throwable {
                    return jdbcRule.getConnectionSource().getConnection();
                }
            });
            return dataSource;
        } catch (final SQLException e) {
            Throwables.rethrow(e);
            throw new InternalError("unreachable");
        }
    }
