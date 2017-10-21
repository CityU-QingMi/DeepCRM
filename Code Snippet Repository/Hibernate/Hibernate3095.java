	@Override
	public final Object internalLoad(String entityName, Serializable id, boolean eager, boolean nullable)
			throws HibernateException {
		// todo : remove
		LoadEventListener.LoadType type = nullable
				? LoadEventListener.INTERNAL_LOAD_NULLABLE
				: eager
				? LoadEventListener.INTERNAL_LOAD_EAGER
				: LoadEventListener.INTERNAL_LOAD_LAZY;

		LoadEvent event = loadEvent;
		loadEvent = null;
		event = recycleEventInstance( event, id, entityName );
		fireLoad( event, type );
		Object result = event.getResult();
		if ( !nullable ) {
			UnresolvableObjectException.throwIfNull( result, id, entityName );
		}
		if ( loadEvent == null ) {
			event.setEntityClassName( null );
			event.setEntityId( null );
			event.setInstanceToLoad( null );
			event.setResult( null );
			loadEvent = event;
		}
		return result;
	}
