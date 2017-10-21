	@Override
	protected Attribute locateAttributeInternal(String attributeName) {
		final Attribute attribute = managedType.getAttribute( attributeName );
		// ManagedType.locateAttribute should throw exception rather than return
		// null, but just to be safe...
		if ( attribute == null ) {
			throw new IllegalArgumentException( "Could not resolve attribute named " + attributeName );
		}
		return attribute;
	}
