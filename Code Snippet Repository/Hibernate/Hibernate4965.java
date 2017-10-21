	private static Serializable persistTransientEntity(
			Object entity,
			SharedSessionContractImplementor session) {
		assert session != null;

		LOG.debug( "Performing implicit derived identity cascade" );
		final PersistEvent event = new PersistEvent(
				null,
				entity,
				(EventSource) session
		);

		for ( PersistEventListener listener : persistEventListeners( session ) ) {
			listener.onPersist( event );
		}
		final EntityEntry pcEntry = session.getPersistenceContext().getEntry( entity );
		if ( pcEntry == null || pcEntry.getId() == null ) {
			throw new HibernateException( "Unable to process implicit derived identity cascade" );
		}
		return pcEntry.getId();
	}
