	protected Serializable entityIsTransient(SaveOrUpdateEvent event) {

		LOG.trace( "Saving transient instance" );

		final EventSource source = event.getSession();

		EntityEntry entityEntry = event.getEntry();
		if ( entityEntry != null ) {
			if ( entityEntry.getStatus() == Status.DELETED ) {
				source.forceFlush( entityEntry );
			}
			else {
				throw new AssertionFailure( "entity was persistent" );
			}
		}

		Serializable id = saveWithGeneratedOrRequestedId( event );

		source.getPersistenceContext().reassociateProxy( event.getObject(), id );

		return id;
	}
