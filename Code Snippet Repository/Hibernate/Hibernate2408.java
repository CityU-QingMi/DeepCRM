	protected void doEvict(
			final Object object,
			final EntityKey key,
			final EntityPersister persister,
			final EventSource session)
			throws HibernateException {

		if ( LOG.isTraceEnabled() ) {
			LOG.tracev( "Evicting {0}", MessageHelper.infoString( persister ) );
		}

		if ( persister.hasNaturalIdentifier() ) {
			session.getPersistenceContext().getNaturalIdHelper().handleEviction(
					object,
					persister,
					key.getIdentifier()
			);
		}

		// remove all collections for the entity from the session-level cache
		if ( persister.hasCollections() ) {
			new EvictVisitor( session ).process( object, persister );
		}

		// remove any snapshot, not really for memory management purposes, but
		// rather because it might now be stale, and there is no longer any
		// EntityEntry to take precedence
		// This is now handled by removeEntity()
		//session.getPersistenceContext().removeDatabaseSnapshot(key);

		Cascade.cascade( CascadingActions.EVICT, CascadePoint.AFTER_EVICT, session, persister, object );
	}
