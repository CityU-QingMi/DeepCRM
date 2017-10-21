	@Override
	public void release() {
		JdbcConnectionAccess connectionAccess = jdbcContext.getJdbcConnectionAccess();
		if( !( connectionAccess instanceof JdbcConnectionAccessProvidedConnectionImpl ) ) {
			throw new IllegalStateException(
				"DdlTransactionIsolatorProvidedConnectionImpl should always use a JdbcConnectionAccessProvidedConnectionImpl"
			);
		}
		try {
			// While passing the connection to the releaseConnection method might be suitable for other `JdbcConnectionAccess` implementations,
			// it has no meaning for JdbcConnectionAccessProvidedConnectionImpl because, in this case, the connection is wrapped
			// and we don't have access to it upon releasing via the DdlTransactionIsolatorProvidedConnectionImpl.
			connectionAccess.releaseConnection( null );
		}
		catch (SQLException ignore) {
			LOG.unableToReleaseIsolatedConnection( ignore );
		}
	}
