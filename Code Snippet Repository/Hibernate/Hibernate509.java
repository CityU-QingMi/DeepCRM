	@Override
	public void afterDeserialize(SharedSessionContractImplementor session) {
		super.afterDeserialize( session );
		// IMPL NOTE: non-flushed changes code calls this method with session == null...
		// guard against NullPointerException
		if ( session != null ) {
			final EntityEntry entityEntry = session.getPersistenceContext().getEntry( getInstance() );
			this.state = entityEntry.getLoadedState();
		}
	}
