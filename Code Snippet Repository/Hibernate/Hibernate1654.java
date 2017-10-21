	@SuppressWarnings("")
	private boolean isPropertyIncluded(Object value, String name, Type type) {
		if ( excludedProperties.contains( name ) ) {
			// was explicitly excluded
			return false;
		}

		if ( type.isAssociationType() ) {
			// associations are implicitly excluded
			return false;
		}

		return selector.include( value, name, type );
	}
