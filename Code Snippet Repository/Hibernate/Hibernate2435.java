	private EntityPersister getPersister( final LoadEvent event ) {
		if ( event.getInstanceToLoad() != null ) {
			//the load() which takes an entity does not pass an entityName
			event.setEntityClassName( event.getInstanceToLoad().getClass().getName() );
			return event.getSession().getEntityPersister(
					null,
					event.getInstanceToLoad()
			);
		}
		else {
			return event.getSession().getFactory().getEntityPersister( event.getEntityClassName() );
		}
	}
