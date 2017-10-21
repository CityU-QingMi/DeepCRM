	protected void cascadeBeforeDelete(
			EventSource session,
			EntityPersister persister,
			Object entity,
			EntityEntry entityEntry,
			Set transientEntities) throws HibernateException {

		CacheMode cacheMode = session.getCacheMode();
		session.setCacheMode( CacheMode.GET );
		session.getPersistenceContext().incrementCascadeLevel();
		try {
			// cascade-delete to collections BEFORE the collection owner is deleted
			Cascade.cascade(
					CascadingActions.DELETE,
					CascadePoint.AFTER_INSERT_BEFORE_DELETE,
					session,
					persister,
					entity,
					transientEntities
			);
		}
		finally {
			session.getPersistenceContext().decrementCascadeLevel();
			session.setCacheMode( cacheMode );
		}
	}
