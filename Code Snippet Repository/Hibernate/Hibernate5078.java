	@Override
	public Date seed(SharedSessionContractImplementor session) {
		if ( session == null ) {
			LOG.trace( "Incoming session was null; using current jvm time" );
			return super.seed( null );
		}
		else if ( !session.getJdbcServices().getJdbcEnvironment().getDialect().supportsCurrentTimestampSelection() ) {
			LOG.debug( "Falling back to vm-based timestamp, as dialect does not support current timestamp selection" );
			return super.seed( session );
		}
		else {
			return getCurrentTimestamp( session );
		}
	}
