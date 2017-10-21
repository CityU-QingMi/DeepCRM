	private StartElement transform(StartElement startElement) {
		String elementName = startElement.getName().getLocalPart();
		// use the start element to determine whether we have a persistence.xml or orm.xml
		if ( START_ELEMENT_TO_NAMESPACE_URI.containsKey( elementName ) ) {
			currentDocumentNamespaceUri = START_ELEMENT_TO_NAMESPACE_URI.get( elementName );
		}

		List<Attribute> newElementAttributeList = updateElementAttributes( startElement );
		List<Namespace> newNamespaceList = updateElementNamespaces( startElement );

		// create the new element
		return xmlEventFactory.createStartElement(
				new QName( currentDocumentNamespaceUri, startElement.getName().getLocalPart() ),
				newElementAttributeList.iterator(),
				newNamespaceList.iterator()
		);
	}
