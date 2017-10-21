	private StartElement wrap(StartElement startElement) {
		List<Attribute> newElementAttributeList = mapAttributes( startElement );
		List<Namespace> newNamespaceList = mapNamespaces( startElement );

		// Transfer the location info from the incoming event to the event factory
		// so that the event we ask it to generate for us has the same location info
		xmlEventFactory.setLocation( startElement.getLocation() );
		return xmlEventFactory.createStartElement(
				new QName( LocalSchema.ORM.getNamespaceUri(), startElement.getName().getLocalPart() ),
				newElementAttributeList.iterator(),
				newNamespaceList.iterator()
		);
	}
