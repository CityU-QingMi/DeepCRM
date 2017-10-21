	public BytecodeEnhancementMetadataPojoImpl(
			String entityName,
			Class entityClass,
			boolean enhancedForLazyLoading,
			LazyAttributesMetadata lazyAttributesMetadata) {
		this.entityName = entityName;
		this.entityClass = entityClass;
		this.enhancedForLazyLoading = enhancedForLazyLoading;
		this.lazyAttributesMetadata = lazyAttributesMetadata;
	}
