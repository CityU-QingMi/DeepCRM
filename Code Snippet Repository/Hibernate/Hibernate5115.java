	@SuppressWarnings({ "" })
	private void scheduleBatchLoadIfNeeded(Serializable id, SharedSessionContractImplementor session) throws MappingException {
		//cannot batch fetch by unique key (property-ref associations)
		if ( uniqueKeyPropertyName == null && id != null ) {
			final EntityPersister persister = getAssociatedEntityPersister( session.getFactory() );
			if ( persister.isBatchLoadable() ) {
				final EntityKey entityKey = session.generateEntityKey( id, persister );
				if ( !session.getPersistenceContext().containsEntity( entityKey ) ) {
					session.getPersistenceContext().getBatchFetchQueue().addBatchLoadableEntityKey( entityKey );
				}
			}
		}
	}
