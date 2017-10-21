	@Override
	public PreparedStatement getBatchStatement(String sql, boolean callable) {
		if ( sql == null ) {
			throw new IllegalArgumentException( "sql must be non-null." );
		}
		PreparedStatement statement = statements.get( sql );
		if ( statement == null ) {
			statement = buildBatchStatement( sql, callable );
			statements.put( sql, statement );
		}
		else {
			LOG.debug( "Reusing batch statement" );
			sqlStatementLogger().logStatement( sql );
		}
		return statement;
	}
