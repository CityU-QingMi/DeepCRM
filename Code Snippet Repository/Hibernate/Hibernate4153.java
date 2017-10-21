	private boolean isValueGenerationRequired(NonIdentifierAttribute attribute, GenerationTiming matchTiming) {
		if ( attribute.getType() instanceof ComponentType ) {
			final ComponentType type = (ComponentType) attribute.getType();
			final ValueGeneration[] propertyValueGenerationStrategies = type.getPropertyValueGenerationStrategies();
			for ( int i = 0; i < propertyValueGenerationStrategies.length; i++ ) {
				if ( isReadRequired( propertyValueGenerationStrategies[i], matchTiming ) ) {
					return true;
				}
			}
			return false;
		}
		else {
			return isReadRequired( attribute.getValueGenerationStrategy(), matchTiming );
		}
	}
