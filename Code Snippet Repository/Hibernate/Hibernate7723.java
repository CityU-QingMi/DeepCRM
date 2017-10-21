	public CollectionListeners( SessionFactory sf) {
		preCollectionRecreateListener = new PreCollectionRecreateListener( this );
		initializeCollectionListener = new InitializeCollectionListener( this );
		preCollectionRemoveListener = new PreCollectionRemoveListener( this );
		preCollectionUpdateListener = new PreCollectionUpdateListener( this );
		postCollectionRecreateListener = new PostCollectionRecreateListener( this );
		postCollectionRemoveListener = new PostCollectionRemoveListener( this );
		postCollectionUpdateListener = new PostCollectionUpdateListener( this );

		EventListenerRegistry registry = ( (SessionFactoryImplementor) sf ).getServiceRegistry().getService( EventListenerRegistry.class );
		registry.setListeners( EventType.INIT_COLLECTION, initializeCollectionListener );

		registry.setListeners( EventType.PRE_COLLECTION_RECREATE, preCollectionRecreateListener );
		registry.setListeners( EventType.POST_COLLECTION_RECREATE, postCollectionRecreateListener );

		registry.setListeners( EventType.PRE_COLLECTION_REMOVE, preCollectionRemoveListener );
		registry.setListeners( EventType.POST_COLLECTION_REMOVE, postCollectionRemoveListener );

		registry.setListeners( EventType.PRE_COLLECTION_UPDATE, preCollectionUpdateListener );
		registry.setListeners( EventType.POST_COLLECTION_UPDATE, postCollectionUpdateListener );
	}
