	private XMLEvent wrap(EndElement endElement) {
		final List<Namespace> targetNamespaces = mapNamespaces( existingXmlNamespacesIterator( endElement ) );

		// Transfer the location info from the incoming event to the event factory
		// so that the event we ask it to generate for us has the same location info
		xmlEventFactory.setLocation( endElement.getLocation() );
		return xmlEventFactory.createEndElement(
				new QName( LocalSchema.ORM.getNamespaceUri(), endElement.getName().getLocalPart() ),
				targetNamespaces.iterator()
		);
	}
