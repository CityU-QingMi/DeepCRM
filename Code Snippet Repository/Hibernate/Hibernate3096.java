	private LoadEvent recycleEventInstance(final LoadEvent event, final Serializable id, final String entityName) {
		if ( event == null ) {
			return new LoadEvent( id, entityName, true, this );
		}
		else {
			event.setEntityClassName( entityName );
			event.setEntityId( id );
			event.setInstanceToLoad( null );
			event.setLockMode( LoadEvent.DEFAULT_LOCK_MODE );
			event.setLockScope( LoadEvent.DEFAULT_LOCK_OPTIONS.getScope() );
			event.setLockTimeout( LoadEvent.DEFAULT_LOCK_OPTIONS.getTimeOut() );
			return event;
		}
	}
