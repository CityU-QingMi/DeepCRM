	@Override
	public void release(Statement statement) {
		log.tracev( "Releasing statement [{0}]", statement );

		// Keep this at DEBUG level, rather than warn.  Numerous connection pool implementations can return a
		// proxy/wrapper around the JDBC Statement, causing excessive logging here.  See HHH-8210.
		if ( log.isDebugEnabled() && !xref.containsKey( statement ) ) {
			log.unregisteredStatement();
		}
		else {
			final Set<ResultSet> resultSets = xref.get( statement );
			if ( resultSets != null ) {
				closeAll( resultSets );
			}
			xref.remove( statement );
		}
		close( statement );

		if ( lastQuery == statement ) {
			lastQuery = null;
		}
	}
