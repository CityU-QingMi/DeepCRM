	protected void cascadeAfterDelete(
			EventSource session,
			EntityPersister persister,
			Object entity,
			Set transientEntities) throws HibernateException {

		CacheMode cacheMode = session.getCacheMode();
		session.setCacheMode( CacheMode.GET );
		session.getPersistenceContext().incrementCascadeLevel();
		try {
			// cascade-delete to many-to-one AFTER the parent was deleted
			Cascade.cascade(
					CascadingActions.DELETE,
					CascadePoint.BEFORE_INSERT_AFTER_DELETE,
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
