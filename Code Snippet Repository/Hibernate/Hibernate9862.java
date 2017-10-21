	private void applyNestedType(SimpleValue value, Element propertyMapping) {
		final Properties typeParameters = value.getTypeParameters();
		final Element typeMapping = propertyMapping.addElement( "type" );
		final String typeName = getBasicTypeName( value.getType() );

		typeMapping.addAttribute( "name", typeName );

		if ( EnumType.class.getName().equals( typeName ) ) {
			// Proper handling of enumeration type
			mapEnumerationType( typeMapping, value.getType(), typeParameters );
		}
		else {
			// By default copying all Hibernate properties
			for ( Object object : typeParameters.keySet() ) {
				final String keyType = (String) object;
				final String property = typeParameters.getProperty( keyType );
				if ( property != null ) {
					typeMapping.addElement( "param" ).addAttribute( "name", keyType ).setText( property );
				}
			}
		}
	}
