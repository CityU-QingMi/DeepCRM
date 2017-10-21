	@Override
	public DataSource getDataSource(String dataSourceName) throws DataSourceLookupFailureException {
		try {
			return lookup(dataSourceName, DataSource.class);
		}
		catch (NamingException ex) {
			throw new DataSourceLookupFailureException(
					"Failed to look up JNDI DataSource with name '" + dataSourceName + "'", ex);
		}
	}
