	@Override
	public Object immediateLoad(String entityName, Serializable id) throws HibernateException {
		if ( log.isDebugEnabled() ) {
			EntityPersister persister = getFactory().getMetamodel().entityPersister( entityName );
			log.debugf( "Initializing proxy: %s", MessageHelper.infoString( persister, id, getFactory() ) );
		}
		LoadEvent event = loadEvent;
		loadEvent = null;
		event = recycleEventInstance( event, id, entityName );
		fireLoad( event, LoadEventListener.IMMEDIATE_LOAD );
		Object result = event.getResult();
		if ( loadEvent == null ) {
			event.setEntityClassName( null );
			event.setEntityId( null );
			event.setInstanceToLoad( null );
			event.setResult( null );
			loadEvent = event;
		}
		return result;
	}
