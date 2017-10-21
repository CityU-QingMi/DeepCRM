	private List<Namespace> mapNamespaces(Iterator<Namespace> originalNamespaceIterator ) {
		final List<Namespace> mappedNamespaces = new ArrayList<Namespace>();

//		final String elementNamespacePrefix = startElement.getName().getPrefix();
//		if ( EMPTY_NAMESPACE_PREFIX.equals( elementNamespacePrefix ) ) {
//			// add the default namespace mapping
//			mappedNamespaces.add( xmlEventFactory.createNamespace( LocalSchema.ORM.getNamespaceUri() ) );
//		}

		while ( originalNamespaceIterator.hasNext() ) {
			final Namespace originalNamespace  = originalNamespaceIterator.next();
			final Namespace mappedNamespace = mapNamespace( originalNamespace );
			mappedNamespaces.add( mappedNamespace );
		}

		if ( mappedNamespaces.isEmpty() ) {
			mappedNamespaces.add( xmlEventFactory.createNamespace( LocalSchema.ORM.getNamespaceUri() ) );
		}

		return mappedNamespaces;
	}
