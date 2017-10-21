	public static Element addProperty(
			Element parent,
			String name,
			String type,
			boolean insertable,
			boolean updateable,
			boolean key) {
		final Element propMapping;
		if ( key ) {
			propMapping = parent.addElement( "key-property" );
		}
		else {
			propMapping = parent.addElement( "property" );
			propMapping.addAttribute( "insert", Boolean.toString( insertable ) );
			propMapping.addAttribute( "update", Boolean.toString( updateable ) );
		}

		propMapping.addAttribute( "name", name );

		if ( type != null ) {
			propMapping.addAttribute( "type", type );
		}

		return propMapping;
	}
