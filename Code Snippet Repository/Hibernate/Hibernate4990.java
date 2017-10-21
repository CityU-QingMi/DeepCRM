	private ValueInclusion determineInsertValueGenerationType(Property mappingProperty, NonIdentifierAttribute runtimeProperty) {
		if ( isInsertGenerated( runtimeProperty ) ) {
			return ValueInclusion.FULL;
		}
		else if ( mappingProperty.getValue() instanceof Component ) {
			if ( hasPartialInsertComponentGeneration( ( Component ) mappingProperty.getValue() ) ) {
				return ValueInclusion.PARTIAL;
			}
		}
		return ValueInclusion.NONE;
	}
