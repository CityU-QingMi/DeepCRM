	@SuppressWarnings({""})
	public static void prefixNamesInPropertyElement(
			Element element,
			String prefix,
			ColumnNameIterator columnNameIterator,
			boolean changeToKey,
			boolean insertable) {
		final Iterator<Element> properties = element.elementIterator();
		while ( properties.hasNext() ) {
			final Element property = properties.next();

			if ( "property".equals( property.getName() ) || "many-to-one".equals( property.getName() ) ) {
				final Attribute nameAttr = property.attribute( "name" );
				if ( nameAttr != null ) {
					nameAttr.setText( prefix + nameAttr.getText() );
				}

				changeNamesInColumnElement( property, columnNameIterator );

				if ( changeToKey ) {
					property.setName( "key-" + property.getName() );

					// HHH-11463 when cloning a many-to-one to be a key-many-to-one, the FK attribute
					// should be explicitly set to 'none' or added to be 'none' to avoid issues with
					// making references to the main schema.
					if ( property.getName().equals( "key-many-to-one" ) ) {
						final Attribute foreignKey = property.attribute( "foreign-key" );
						if ( foreignKey == null ) {
							property.addAttribute( "foreign-key", "none" );
						}
						else {
							foreignKey.setValue( "none" );
						}
					}
				}

				if ( "property".equals( property.getName() ) ) {
					final Attribute insert = property.attribute( "insert" );
					insert.setText( Boolean.toString( insertable ) );
				}
			}
		}
	}
