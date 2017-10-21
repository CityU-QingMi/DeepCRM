	public void onLoad(
			final LoadEvent event,
			final LoadEventListener.LoadType loadType) throws HibernateException {

		final EntityPersister persister = getPersister( event );

		if ( persister == null ) {
			throw new HibernateException( "Unable to locate persister: " + event.getEntityClassName() );
		}

		final Class idClass = persister.getIdentifierType().getReturnedClass();
		if ( idClass != null && !idClass.isInstance( event.getEntityId() ) ) {
			checkIdClass( persister, event, loadType, idClass );
		}

		doOnLoad( persister, event, loadType );
	}
