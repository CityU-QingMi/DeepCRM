	@Override
	public Object[] getPropertyValues(Object entity) {
		final BytecodeEnhancementMetadata enhancementMetadata = entityMetamodel.getBytecodeEnhancementMetadata();
		final int span = entityMetamodel.getPropertySpan();
		final Object[] result = new Object[span];

		for ( int j = 0; j < span; j++ ) {
			NonIdentifierAttribute property = entityMetamodel.getProperties()[j];
			if ( !property.isLazy() || enhancementMetadata.isAttributeLoaded( entity, property.getName() ) ) {
				result[j] = getters[j].get( entity );
			}
			else {
				result[j] = LazyPropertyInitializer.UNFETCHED_PROPERTY;
			}
		}
		return result;
	}
