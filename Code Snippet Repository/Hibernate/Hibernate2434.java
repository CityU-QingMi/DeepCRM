	private void makeEntityCircularReferenceSafe(ReferenceCacheEntryImpl referenceCacheEntry,
												EventSource session,
												Object entity,
												EntityKey entityKey) {

		// make it circular-reference safe
		final StatefulPersistenceContext statefulPersistenceContext = (StatefulPersistenceContext) session.getPersistenceContext();

		if ( (entity instanceof ManagedEntity) ) {
			statefulPersistenceContext.addReferenceEntry(
					entity,
					Status.READ_ONLY
			);
		}
		else {
			TwoPhaseLoad.addUninitializedCachedEntity(
					entityKey,
					entity,
					referenceCacheEntry.getSubclassPersister(),
					LockMode.NONE,
					referenceCacheEntry.getVersion(),
					session
			);
		}
		statefulPersistenceContext.initializeNonLazyCollections();
	}
