	@Override
	public LazyAttributeLoadingInterceptor injectInterceptor(Object entity, SharedSessionContractImplementor session) {
		if ( !enhancedForLazyLoading ) {
			throw new NotInstrumentedException( "Entity class [" + entityClass.getName() + "] is not enhanced for lazy loading" );
		}

		if ( !entityClass.isInstance( entity ) ) {
			throw new IllegalArgumentException(
					String.format(
							"Passed entity instance [%s] is not of expected type [%s]",
							entity,
							getEntityName()
					)
			);
		}

		final LazyAttributeLoadingInterceptor interceptor = new LazyAttributeLoadingInterceptor(
				getEntityName(),
				lazyAttributesMetadata.getLazyAttributeNames(),
				session
		);
		( (PersistentAttributeInterceptable) entity ).$$_hibernate_setInterceptor( interceptor );
		return interceptor;
	}
