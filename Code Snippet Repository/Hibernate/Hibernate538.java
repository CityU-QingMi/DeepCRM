	@Override
	public void afterDeserialize(SharedSessionContractImplementor session) {
		if ( this.session != null || this.persister != null ) {
			throw new IllegalStateException( "already attached to a session." );
		}
		// IMPL NOTE: non-flushed changes code calls this method with session == null...
		// guard against NullPointerException
		if ( session != null ) {
			this.session = session;
			this.persister = session.getFactory().getMetamodel().entityPersister( entityName );
			this.instance = session.getPersistenceContext().getEntity( session.generateEntityKey( id, persister ) );
		}
	}
