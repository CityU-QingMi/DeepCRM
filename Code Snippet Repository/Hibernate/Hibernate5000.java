	@Override
	public void afterInitialize(Object entity, SharedSessionContractImplementor session) {

		// moving to multiple fetch groups, the idea of `lazyPropertiesAreUnfetched` really
		// needs to become either:
		// 		1) the names of all un-fetched fetch groups
		//		2) the names of all fetched fetch groups
		// probably (2) is best
		//
		// ultimately this comes from EntityEntry, although usage-search seems to show it is never updated there.
		//
		// also org.hibernate.persister.entity.AbstractEntityPersister.initializeLazyPropertiesFromDatastore()
		//		needs to be re-worked

		if ( entity instanceof PersistentAttributeInterceptable ) {
			final LazyAttributeLoadingInterceptor interceptor = getEntityMetamodel().getBytecodeEnhancementMetadata().extractInterceptor( entity );
			if ( interceptor == null ) {
				getEntityMetamodel().getBytecodeEnhancementMetadata().injectInterceptor( entity, session );
			}
			else {
				if ( interceptor.getLinkedSession() == null ) {
					interceptor.setSession( session );
				}
			}
		}

		// clear the fields that are marked as dirty in the dirtyness tracker
		if ( entity instanceof SelfDirtinessTracker ) {
			( (SelfDirtinessTracker) entity ).$$_hibernate_clearDirtyAttributes();
		}
	}
