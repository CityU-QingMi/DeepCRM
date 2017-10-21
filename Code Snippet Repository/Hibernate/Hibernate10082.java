	@Override
	@SuppressWarnings({""})
	public <T> T getCurrentRevision(Class<T> revisionEntityClass, boolean persist) {
		revisionEntityClass = getTargetClassIfProxied( revisionEntityClass );
		if ( !(session instanceof EventSource) ) {
			throw new IllegalArgumentException( "The provided session is not an EventSource!" );
		}

		// Obtaining the current audit sync
		final AuditProcess auditProcess = enversService.getAuditProcessManager().get( (EventSource) session );

		// And getting the current revision data
		return (T) auditProcess.getCurrentRevisionData( session, persist );
	}
