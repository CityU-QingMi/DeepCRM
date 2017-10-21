	@Override
	protected Object applyInterception(Object entity) {
		if ( !applyBytecodeInterception ) {
			return entity;
		}

		PersistentAttributeInterceptor interceptor = new LazyAttributeLoadingInterceptor(
				entityMetamodel.getName(),
				entityMetamodel.getBytecodeEnhancementMetadata()
						.getLazyAttributesMetadata()
						.getLazyAttributeNames(),
				null
		);
		( (PersistentAttributeInterceptable) entity ).$$_hibernate_setInterceptor( interceptor );
		return entity;
	}
