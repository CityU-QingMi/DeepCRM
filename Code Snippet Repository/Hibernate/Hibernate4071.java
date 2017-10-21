	private CollectionInitializer getSubselectInitializer(Serializable key, SharedSessionContractImplementor session) {

		if ( !isSubselectLoadable() ) {
			return null;
		}

		final PersistenceContext persistenceContext = session.getPersistenceContext();

		SubselectFetch subselect = persistenceContext.getBatchFetchQueue()
				.getSubselect( session.generateEntityKey( key, getOwnerEntityPersister() ) );

		if ( subselect == null ) {
			return null;
		}
		else {

			// Take care of any entities that might have
			// been evicted!
			Iterator iter = subselect.getResult().iterator();
			while ( iter.hasNext() ) {
				if ( !persistenceContext.containsEntity( (EntityKey) iter.next() ) ) {
					iter.remove();
				}
			}

			// Run a subquery loader
			return createSubselectInitializer( subselect, session );
		}
	}
