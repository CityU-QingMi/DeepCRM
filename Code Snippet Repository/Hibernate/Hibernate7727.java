		protected void integrate(SessionFactoryServiceRegistry serviceRegistry) {
			if ( listener != null ) {
				log.warn( "integrate called second time on testing collection listener Integrator (could be result of rebuilding SF on test failure)" );
			}
			listener = new AggregatedCollectionEventListener();

			final EventListenerRegistry listenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
			listenerRegistry.appendListeners( EventType.INIT_COLLECTION, listener );
			listenerRegistry.appendListeners( EventType.PRE_COLLECTION_RECREATE, listener );
			listenerRegistry.appendListeners( EventType.POST_COLLECTION_RECREATE, listener );
			listenerRegistry.appendListeners( EventType.PRE_COLLECTION_REMOVE, listener );
			listenerRegistry.appendListeners( EventType.POST_COLLECTION_REMOVE, listener );
			listenerRegistry.appendListeners( EventType.PRE_COLLECTION_UPDATE, listener );
			listenerRegistry.appendListeners( EventType.POST_COLLECTION_UPDATE, listener );
		}
