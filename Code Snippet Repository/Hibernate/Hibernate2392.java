	protected EntityState getEntityState(
			Object entity,
			String entityName,
			EntityEntry entry, //pass this as an argument only to avoid double looking
			SessionImplementor source) {

		final boolean traceEnabled = LOG.isTraceEnabled();
		if ( entry != null ) { // the object is persistent

			//the entity is associated with the session, so check its status
			if ( entry.getStatus() != Status.DELETED ) {
				// do nothing for persistent instances
				if ( traceEnabled ) {
					LOG.tracev( "Persistent instance of: {0}", getLoggableName( entityName, entity ) );
				}
				return EntityState.PERSISTENT;
			}
			// ie. e.status==DELETED
			if ( traceEnabled ) {
				LOG.tracev( "Deleted instance of: {0}", getLoggableName( entityName, entity ) );
			}
			return EntityState.DELETED;
		}
		// the object is transient or detached

		// the entity is not associated with the session, so
		// try interceptor and unsaved-value

		if ( ForeignKeys.isTransient( entityName, entity, getAssumedUnsaved(), source ) ) {
			if ( traceEnabled ) {
				LOG.tracev( "Transient instance of: {0}", getLoggableName( entityName, entity ) );
			}
			return EntityState.TRANSIENT;
		}
		if ( traceEnabled ) {
			LOG.tracev( "Detached instance of: {0}", getLoggableName( entityName, entity ) );
		}
		return EntityState.DETACHED;
	}
