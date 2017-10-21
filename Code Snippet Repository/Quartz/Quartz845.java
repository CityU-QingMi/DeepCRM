    private void validateC3p0PoolProviderClassWithExtraProps() throws Exception {
        DBConnectionManager dbManager = DBConnectionManager.getInstance();
        ConnectionProvider provider = dbManager.getConnectionProvider("myDS");

        ComboPooledDataSource ds = ((C3p0PoolingConnectionProvider)provider).getDataSource();

        Assert.assertThat(ds.getDriverClass(), Matchers.is("org.apache.derby.jdbc.ClientDriver"));
        Assert.assertThat(ds.getJdbcUrl(), Matchers.is(JdbcQuartzDerbyUtilities.DATABASE_CONNECTION_PREFIX));
        Assert.assertThat(ds.getUser(), Matchers.is("quartz"));
        Assert.assertThat(ds.getPassword(), Matchers.is("quartz"));
        Assert.assertThat(ds.getMaxPoolSize(), Matchers.is(5));

        Assert.assertThat(ds.getMinPoolSize(), Matchers.is(5));
        Assert.assertThat(ds.getAcquireIncrement(), Matchers.is(5));
        Assert.assertThat(ds.getAcquireRetryAttempts(), Matchers.is(3));
        Assert.assertThat(ds.getAcquireRetryDelay(), Matchers.is(3000));
    }
