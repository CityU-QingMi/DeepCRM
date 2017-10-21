	@Override
	public void load(Object object, Serializable id) throws HibernateException {
		LoadEvent event = loadEvent;
		loadEvent = null;
		if ( event == null ) {
			event = new LoadEvent( id, object, this );
		}
		else {
			event.setEntityClassName( null );
			event.setEntityId( id );
			event.setInstanceToLoad( object );
			event.setLockMode( LoadEvent.DEFAULT_LOCK_MODE );
			event.setLockScope( LoadEvent.DEFAULT_LOCK_OPTIONS.getScope() );
			event.setLockTimeout( LoadEvent.DEFAULT_LOCK_OPTIONS.getTimeOut() );
		}

		fireLoad( event, LoadEventListener.RELOAD );

		if ( loadEvent == null ) {
			event.setEntityClassName( null );
			event.setEntityId( null );
			event.setInstanceToLoad( null );
			event.setResult( null );
			loadEvent = event;
		}
	}
