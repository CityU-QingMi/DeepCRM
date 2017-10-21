	private void executeInSession(Session session) {
		// Making sure the revision data is persisted.
		final Object currentRevisionData = getCurrentRevisionData( session, true );

		AuditWorkUnit vwu;

		// First undoing any performed work units
		while ( (vwu = undoQueue.poll()) != null ) {
			vwu.undo( session );
		}

		while ( (vwu = workUnits.poll()) != null ) {
			vwu.perform( session, revisionData );
			entityChangeNotifier.entityChanged( session, currentRevisionData, vwu );
		}
	}
