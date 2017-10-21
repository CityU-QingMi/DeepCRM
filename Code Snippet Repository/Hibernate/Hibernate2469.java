	public void onSaveOrUpdate(SaveOrUpdateEvent event) {
		final SessionImplementor source = event.getSession();
		final Object object = event.getObject();
		final Serializable requestedId = event.getRequestedId();

		if ( requestedId != null ) {
			//assign the requested id to the proxy, *before*
			//reassociating the proxy
			if ( object instanceof HibernateProxy ) {
				( (HibernateProxy) object ).getHibernateLazyInitializer().setIdentifier( requestedId );
			}
		}

		// For an uninitialized proxy, noop, don't even need to return an id, since it is never a save()
		if ( reassociateIfUninitializedProxy( object, source ) ) {
			LOG.trace( "Reassociated uninitialized proxy" );
		}
		else {
			//initialize properties of the event:
			final Object entity = source.getPersistenceContext().unproxyAndReassociate( object );
			event.setEntity( entity );
			event.setEntry( source.getPersistenceContext().getEntry( entity ) );
			//return the id in the event object
			event.setResultId( performSaveOrUpdate( event ) );
		}

	}
