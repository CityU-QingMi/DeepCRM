	public static BytecodeEnhancementMetadata from(PersistentClass persistentClass) {
		final Class mappedClass = persistentClass.getMappedClass();
		final boolean enhancedForLazyLoading = PersistentAttributeInterceptable.class.isAssignableFrom( mappedClass );
		final LazyAttributesMetadata lazyAttributesMetadata = enhancedForLazyLoading
				? LazyAttributesMetadata.from( persistentClass )
				: LazyAttributesMetadata.nonEnhanced( persistentClass.getEntityName() );

		return new BytecodeEnhancementMetadataPojoImpl(
				persistentClass.getEntityName(),
				mappedClass,
				enhancedForLazyLoading,
				lazyAttributesMetadata
		);
	}
