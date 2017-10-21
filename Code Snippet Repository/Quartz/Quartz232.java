    private void populateProviderWithExtraProps(PoolingConnectionProvider cp, Properties props) throws Exception {
        Properties copyProps = new Properties();
        copyProps.putAll(props);

        // Remove all the default properties first (they don't always match to setter name, and they are already
        // been set!)
        copyProps.remove(PoolingConnectionProvider.DB_DRIVER);
        copyProps.remove(PoolingConnectionProvider.DB_URL);
        copyProps.remove(PoolingConnectionProvider.DB_USER);
        copyProps.remove(PoolingConnectionProvider.DB_PASSWORD);
        copyProps.remove(PoolingConnectionProvider.DB_MAX_CONNECTIONS);
        copyProps.remove(PoolingConnectionProvider.DB_VALIDATION_QUERY);
        props.remove(PoolingConnectionProvider.POOLING_PROVIDER);
        setBeanProps(cp.getDataSource(), copyProps);
    }
