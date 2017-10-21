	public JdbcResultMetadata(SessionFactoryImplementor factory, ResultSet resultSet) throws HibernateException {
		try {
			this.factory = factory;
			this.resultSet = resultSet;
			this.resultSetMetaData = resultSet.getMetaData();
		}
		catch( SQLException e ) {
			throw new HibernateException( "Could not extract result set metadata", e );
		}
	}
