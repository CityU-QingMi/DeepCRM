	private ValueInclusion determineUpdateValueGenerationType(Property mappingProperty, NonIdentifierAttribute runtimeProperty) {
		if ( isUpdateGenerated( runtimeProperty ) ) {
			return ValueInclusion.FULL;
		}
		else if ( mappingProperty.getValue() instanceof Component ) {
			if ( hasPartialUpdateComponentGeneration( ( Component ) mappingProperty.getValue() ) ) {
				return ValueInclusion.PARTIAL;
			}
		}
		return ValueInclusion.NONE;
	}
