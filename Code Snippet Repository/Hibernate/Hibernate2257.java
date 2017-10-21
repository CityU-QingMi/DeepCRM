	@Override
	public void traceLog(TraceLogger logger) {
		if ( NativeSQLQueryRootReturn.class.isInstance( this ) ) {
			logger.writeLine( "Entity(...)" );
		}
		else if ( NativeSQLQueryCollectionReturn.class.isInstance( this ) ) {
			logger.writeLine( "Collection(...)" );
		}
		else if ( NativeSQLQueryJoinReturn.class.isInstance( this ) ) {
			logger.writeLine( "Join(...)" );
		}
		else {
			logger.writeLine( getClass().getName() + "(...)" );
		}
	}
