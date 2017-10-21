    public void shutdown(String dsName) throws SQLException {

        ConnectionProvider provider = (ConnectionProvider) providers
        .get(dsName);
        if (provider == null) {
            throw new SQLException("There is no DataSource named '"
                    + dsName + "'");
        }

        provider.shutdown();

    }
