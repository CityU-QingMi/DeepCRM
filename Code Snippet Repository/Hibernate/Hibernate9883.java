	@SuppressWarnings({""})
	private static void changeNamesInColumnElement(Element element, ColumnNameIterator columnNameIterator) {
		final Iterator<Element> properties = element.elementIterator();
		while ( properties.hasNext() ) {
			final Element property = properties.next();

			if ( "column".equals( property.getName() ) ) {
				final Attribute nameAttr = property.attribute( "name" );
				if ( nameAttr != null ) {
					nameAttr.setText( columnNameIterator.next() );
				}
			}
		}
	}
