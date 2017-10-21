		private StartElement withNamespace(StartElement startElement) {
			// otherwise, wrap the start element event to provide a default namespace mapping
			final List<Namespace> namespaces = new ArrayList<Namespace>();
			namespaces.add( xmlEventFactory.createNamespace( "", namespaceUri ) );
			Iterator<?> originalNamespaces = startElement.getNamespaces();
			while ( originalNamespaces.hasNext() ) {
				namespaces.add( (Namespace) originalNamespaces.next() );
			}
			return xmlEventFactory.createStartElement(
					new QName( namespaceUri, startElement.getName().getLocalPart() ),
					startElement.getAttributes(),
					namespaces.iterator()
			);
		}
