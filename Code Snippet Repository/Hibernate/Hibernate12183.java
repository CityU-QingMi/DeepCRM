	private List<Namespace> updateElementNamespaces(StartElement startElement) {
		List<Namespace> newNamespaceList = new ArrayList<Namespace>();
		Iterator<?> existingNamespaceIterator = startElement.getNamespaces();
		while ( existingNamespaceIterator.hasNext() ) {
			Namespace namespace = (Namespace) existingNamespaceIterator.next();
			if ( NAMESPACE_MAPPING.containsKey( namespace.getNamespaceURI() ) ) {
				newNamespaceList.add( xmlEventFactory.createNamespace( EMPTY_PREFIX, currentDocumentNamespaceUri ) );
			}
			else {
				newNamespaceList.add( namespace );
			}
		}

		// if there is no namespace at all we add the main one. All elements need the namespace
		if ( newNamespaceList.isEmpty() ) {
			newNamespaceList.add( xmlEventFactory.createNamespace( EMPTY_PREFIX, currentDocumentNamespaceUri ) );
		}

		return newNamespaceList;
	}
