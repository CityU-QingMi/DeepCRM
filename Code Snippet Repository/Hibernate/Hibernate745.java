	@SuppressWarnings("")
	private StartElement applyNamespace(StartElement startElement) {
		final List<Namespace> targetNamespaces = new ArrayList<Namespace>();

		if ( "".equals( startElement.getName().getNamespaceURI() ) ) {
			// add the default namespace mapping
			targetNamespaces.add( xmlEventFactory.createNamespace( LocalSchema.HBM.getNamespaceUri() ) );
		}

		// transfer any namespaces directly, unless it is in the "to map" list in which case
		// we transfer a mapped copy pointing to the new namespace
		final Iterator<Namespace> originalNamespaces = startElement.getNamespaces();
		while ( originalNamespaces.hasNext() ) {
			Namespace namespace = originalNamespaces.next();
			if ( NAMESPACE_URIS_TO_MAP.contains( namespace.getNamespaceURI() ) ) {
				// this is a namespace "to map" so map it
				namespace = xmlEventFactory.createNamespace( namespace.getPrefix(), LocalSchema.HBM.getNamespaceUri() );
			}
			targetNamespaces.add( namespace );
		}

		// Transfer the location info from the incoming event to the event factory
		// so that the event we ask it to generate for us has the same location info
		xmlEventFactory.setLocation( startElement.getLocation() );
		return xmlEventFactory.createStartElement(
				new QName( LocalSchema.HBM.getNamespaceUri(), startElement.getName().getLocalPart() ),
				startElement.getAttributes(),
				targetNamespaces.iterator()
		);
	}
