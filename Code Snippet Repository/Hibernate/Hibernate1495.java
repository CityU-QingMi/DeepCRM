	public static TableGenerator buildTableGeneratorAnnotation(Element element, XMLContext.Default defaults) {
		AnnotationDescriptor ad = new AnnotationDescriptor( TableGenerator.class );
		copyStringAttribute( ad, element, "name", false );
		copyStringAttribute( ad, element, "table", false );
		copyStringAttribute( ad, element, "catalog", false );
		copyStringAttribute( ad, element, "schema", false );
		copyStringAttribute( ad, element, "pk-column-name", false );
		copyStringAttribute( ad, element, "value-column-name", false );
		copyStringAttribute( ad, element, "pk-column-value", false );
		copyIntegerAttribute( ad, element, "initial-value" );
		copyIntegerAttribute( ad, element, "allocation-size" );
		buildUniqueConstraints( ad, element );
		if ( StringHelper.isEmpty( (String) ad.valueOf( "schema" ) )
				&& StringHelper.isNotEmpty( defaults.getSchema() ) ) {
			ad.setValue( "schema", defaults.getSchema() );
		}
		if ( StringHelper.isEmpty( (String) ad.valueOf( "catalog" ) )
				&& StringHelper.isNotEmpty( defaults.getCatalog() ) ) {
			ad.setValue( "catalog", defaults.getCatalog() );
		}
		return AnnotationFactory.create( ad );
	}
