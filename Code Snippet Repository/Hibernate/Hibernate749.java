	private Attribute mapAttribute(StartElement startElement, Attribute originalAttribute) {
		// Here we look to see if this attribute is the JPA version attribute, and if so do 2 things:
		//		1) validate its version attribute is valid
		//		2) update its version attribute to the default version if not already
		//
		// NOTE : atm this is a very simple check using just the attribute's local name
		// rather than checking its qualified name.  It is possibly (though unlikely)
		// that this could match on "other" version attributes in the same element

		if ( ROOT_ELEMENT_NAME.equals( startElement.getName().getLocalPart() ) ) {
			if ( VERSION_ATTRIBUTE_NAME.equals( originalAttribute.getName().getLocalPart() ) ) {
				final String specifiedVersion = originalAttribute.getValue();

				if ( !VALID_VERSIONS.contains( specifiedVersion ) ) {
					throw new BadVersionException( specifiedVersion );
				}

				return xmlEventFactory.createAttribute( VERSION_ATTRIBUTE_NAME, DEFAULT_VERSION );
			}
		}

		return originalAttribute;
	}
