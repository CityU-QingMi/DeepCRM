	private void checkNotAssociatedWithOtherPersistenceContextIfMutable(ManagedEntity managedEntity) {
		// we only have to check mutable managedEntity
		final AbstractEntityEntry entityEntry = (AbstractEntityEntry) managedEntity.$$_hibernate_getEntityEntry();
		if ( entityEntry == null ||
				!entityEntry.getPersister().isMutable() ||
				entityEntry.getPersistenceContext() == null ||
				entityEntry.getPersistenceContext() == persistenceContext ) {
			return;
		}
		if ( entityEntry.getPersistenceContext().getSession().isOpen() ) {
			// NOTE: otherPersistenceContext may be operating on the entityEntry in a different thread.
			//       it is not safe to associate entityEntry with this EntityEntryContext.
			throw new HibernateException(
					"Illegal attempt to associate a ManagedEntity with two open persistence contexts. " + entityEntry
			);
		}
		else {
			// otherPersistenceContext is associated with a closed PersistenceContext
			log.stalePersistenceContextInEntityEntry( entityEntry.toString() );
		}
	}
