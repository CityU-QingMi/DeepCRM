    private void validateHikariCpPoolProviderClassWithExtraProps() throws Exception {
        DBConnectionManager dbManager = DBConnectionManager.getInstance();
        ConnectionProvider provider = dbManager.getConnectionProvider("myDS");

        HikariDataSource ds = ((HikariCpPoolingConnectionProvider)provider).getDataSource();

        Assert.assertThat(ds.getDriverClassName(), Matchers.is("org.apache.derby.jdbc.ClientDriver"));
        Assert.assertThat(ds.getJdbcUrl(), Matchers.is(JdbcQuartzDerbyUtilities.DATABASE_CONNECTION_PREFIX));
        Assert.assertThat(ds.getUsername(), Matchers.is("quartz"));
        Assert.assertThat(ds.getPassword(), Matchers.is("quartz"));
        Assert.assertThat(ds.getMaximumPoolSize(), Matchers.is(5));

        Assert.assertThat(ds.getTransactionIsolation(), Matchers.is("TRANSACTION_REPEATABLE_READ"));
        Assert.assertThat(ds.isReadOnly(), Matchers.is(false));
        Assert.assertThat(ds.isAutoCommit(), Matchers.is(false));
    }
