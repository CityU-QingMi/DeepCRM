	protected Serializable entityIsPersistent(SaveOrUpdateEvent event) throws HibernateException {
		final boolean traceEnabled = LOG.isTraceEnabled();
		if ( traceEnabled ) {
			LOG.trace( "Ignoring persistent instance" );
		}
		EntityEntry entityEntry = event.getEntry();
		if ( entityEntry == null ) {
			throw new AssertionFailure( "entity was transient or detached" );
		}
		else {

			if ( entityEntry.getStatus() == Status.DELETED ) {
				throw new AssertionFailure( "entity was deleted" );
			}

			final SessionFactoryImplementor factory = event.getSession().getFactory();

			Serializable requestedId = event.getRequestedId();

			Serializable savedId;
			if ( requestedId == null ) {
				savedId = entityEntry.getId();
			}
			else {

				final boolean isEqual = !entityEntry.getPersister().getIdentifierType()
						.isEqual( requestedId, entityEntry.getId(), factory );

				if ( isEqual ) {
					throw new PersistentObjectException(
							"object passed to save() was already persistent: " +
									MessageHelper.infoString( entityEntry.getPersister(), requestedId, factory )
					);
				}

				savedId = requestedId;

			}

			if ( traceEnabled ) {
				LOG.tracev(
						"Object already associated with session: {0}",
						MessageHelper.infoString( entityEntry.getPersister(), savedId, factory )
				);
			}

			return savedId;

		}
	}
