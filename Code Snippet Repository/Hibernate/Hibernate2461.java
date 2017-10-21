	private void evictCachedCollections(Type[] types, Serializable id, EventSource source)
			throws HibernateException {
		for ( Type type : types ) {
			if ( type.isCollectionType() ) {
				CollectionPersister collectionPersister = source.getFactory().getMetamodel().collectionPersister( ( (CollectionType) type ).getRole() );
				if ( collectionPersister.hasCache() ) {
					final CollectionRegionAccessStrategy cache = collectionPersister.getCacheAccessStrategy();
					final Object ck = cache.generateCacheKey(
						id,
						collectionPersister,
						source.getFactory(),
						source.getTenantIdentifier()
					);
					final SoftLock lock = cache.lockItem( source, ck, null );
					source.getActionQueue().registerProcess( new AfterTransactionCompletionProcess() {
						@Override
						public void doAfterTransactionCompletion(boolean success, SharedSessionContractImplementor session) {
							cache.unlockItem( session, ck, lock );
						}
					} );
					cache.remove( source, ck );
				}
			}
			else if ( type.isComponentType() ) {
				CompositeType actype = (CompositeType) type;
				evictCachedCollections( actype.getSubtypes(), id, source );
			}
		}
	}
