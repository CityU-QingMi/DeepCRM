	protected void logStaticSQL() {
		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Static SQL for collection: %s", getRole() );
			if ( getSQLInsertRowString() != null ) {
				LOG.debugf( " Row insert: %s", getSQLInsertRowString() );
			}
			if ( getSQLUpdateRowString() != null ) {
				LOG.debugf( " Row update: %s", getSQLUpdateRowString() );
			}
			if ( getSQLDeleteRowString() != null ) {
				LOG.debugf( " Row delete: %s", getSQLDeleteRowString() );
			}
			if ( getSQLDeleteString() != null ) {
				LOG.debugf( " One-shot delete: %s", getSQLDeleteString() );
			}
		}
	}
