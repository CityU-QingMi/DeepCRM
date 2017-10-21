	@Override
	public JDBCException convert(SQLException sqlException, String message, String sql) {
		for ( SQLExceptionConversionDelegate delegate : delegates ) {
			final JDBCException jdbcException = delegate.convert( sqlException, message, sql );
			if ( jdbcException != null ) {
				return jdbcException;
			}
		}
		return new GenericJDBCException( message, sqlException, sql );
	}
