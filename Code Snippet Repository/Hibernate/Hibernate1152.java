	private void integrate(SessionFactoryServiceRegistry serviceRegistry, SessionFactoryImplementor sessionFactory) {
		if ( !sessionFactory.getSessionFactoryOptions().isAutoEvictCollectionCache() ) {
			// feature is disabled
			return;
		}
		if ( !sessionFactory.getSessionFactoryOptions().isSecondLevelCacheEnabled() ) {
			// Nothing to do, if caching is disabled
			return;
		}
		EventListenerRegistry eventListenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
		eventListenerRegistry.appendListeners( EventType.POST_INSERT, this );
		eventListenerRegistry.appendListeners( EventType.POST_DELETE, this );
		eventListenerRegistry.appendListeners( EventType.POST_UPDATE, this );
	}
