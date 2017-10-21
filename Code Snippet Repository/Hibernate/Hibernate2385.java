	private void cascadeOnFlush(EventSource session, EntityPersister persister, Object object, Object anything)
	throws HibernateException {
		session.getPersistenceContext().incrementCascadeLevel();
		try {
			Cascade.cascade( getCascadingAction(), CascadePoint.BEFORE_FLUSH, session, persister, object, anything );
		}
		finally {
			session.getPersistenceContext().decrementCascadeLevel();
		}
	}
