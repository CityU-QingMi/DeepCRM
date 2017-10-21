	private List<Attribute> updateElementAttributes(StartElement startElement) {
		// adjust the version attribute
		List<Attribute> newElementAttributeList = new ArrayList<Attribute>();
		Iterator<?> existingAttributesIterator = startElement.getAttributes();
		while ( existingAttributesIterator.hasNext() ) {
			Attribute attribute = (Attribute) existingAttributesIterator.next();
			if ( VERSION_ATTRIBUTE_NAME.equals( attribute.getName().getLocalPart() ) ) {
				if ( !DEFAULT_VERSION.equals( attribute.getName().getPrefix() ) ) {
					newElementAttributeList.add(
							xmlEventFactory.createAttribute(
									attribute.getName(),
									DEFAULT_VERSION
							)
					);
				}
			}
			else {
				newElementAttributeList.add( attribute );
			}
		}
		return newElementAttributeList;
	}
