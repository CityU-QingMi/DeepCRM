	@SuppressWarnings( value = {""} )
	private void logFlushResults(FlushEvent event) {
		if ( !LOG.isDebugEnabled() ) {
			return;
		}
		final EventSource session = event.getSession();
		final PersistenceContext persistenceContext = session.getPersistenceContext();
		LOG.debugf(
				"Flushed: %s insertions, %s updates, %s deletions to %s objects",
				session.getActionQueue().numberOfInsertions(),
				session.getActionQueue().numberOfUpdates(),
				session.getActionQueue().numberOfDeletions(),
				persistenceContext.getNumberOfManagedEntities()
		);
		LOG.debugf(
				"Flushed: %s (re)creations, %s updates, %s removals to %s collections",
				session.getActionQueue().numberOfCollectionCreations(),
				session.getActionQueue().numberOfCollectionUpdates(),
				session.getActionQueue().numberOfCollectionRemovals(),
				persistenceContext.getCollectionEntries().size()
		);
		new EntityPrinter( session.getFactory() ).toString(
				persistenceContext.getEntitiesByKey().entrySet()
		);
	}
