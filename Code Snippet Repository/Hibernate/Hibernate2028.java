	@Override
	public void clear() {
		for ( Object o : proxiesByKey.values() ) {
			if ( o == null ) {
				//entry may be GCd
				continue;
			}
			((HibernateProxy) o).getHibernateLazyInitializer().unsetSession();
		}

		for ( Entry<Object, EntityEntry> objectEntityEntryEntry : entityEntryContext.reentrantSafeEntityEntries() ) {
			// todo : I dont think this need be reentrant safe
			if ( objectEntityEntryEntry.getKey() instanceof PersistentAttributeInterceptable ) {
				final PersistentAttributeInterceptor interceptor = ( (PersistentAttributeInterceptable) objectEntityEntryEntry.getKey() ).$$_hibernate_getInterceptor();
				if ( interceptor instanceof LazyAttributeLoadingInterceptor ) {
					( (LazyAttributeLoadingInterceptor) interceptor ).unsetSession();
				}
			}
		}

		for ( Map.Entry<PersistentCollection, CollectionEntry> aCollectionEntryArray : IdentityMap.concurrentEntries( collectionEntries ) ) {
			aCollectionEntryArray.getKey().unsetSession( getSession() );
		}

		arrayHolders.clear();
		entitiesByKey.clear();
		entitiesByUniqueKey.clear();
		entityEntryContext.clear();
//		entityEntries.clear();
		parentsByChild.clear();
		entitySnapshotsByKey.clear();
		collectionsByKey.clear();
		collectionEntries.clear();
		if ( unownedCollections != null ) {
			unownedCollections.clear();
		}
		proxiesByKey.clear();
		nullifiableEntityKeys.clear();
		if ( batchFetchQueue != null ) {
			batchFetchQueue.clear();
		}
		// defaultReadOnly is unaffected by clear()
		hasNonReadOnlyEntities = false;
		if ( loadContexts != null ) {
			loadContexts.cleanup();
		}
		naturalIdXrefDelegate.clear();
	}
