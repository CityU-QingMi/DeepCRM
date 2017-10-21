	private JoinTable buildJoinTable(Element tree, XMLContext.Default defaults) {
		Element subelement = tree == null ? null : tree.element( "join-table" );
		final Class<JoinTable> annotationType = JoinTable.class;
		if ( subelement == null ) {
			return null;
		}
		//ignore java annotation, an element is defined
		AnnotationDescriptor annotation = new AnnotationDescriptor( annotationType );
		copyStringAttribute( annotation, subelement, "name", false );
		copyStringAttribute( annotation, subelement, "catalog", false );
		if ( StringHelper.isNotEmpty( defaults.getCatalog() )
				&& StringHelper.isEmpty( (String) annotation.valueOf( "catalog" ) ) ) {
			annotation.setValue( "catalog", defaults.getCatalog() );
		}
		copyStringAttribute( annotation, subelement, "schema", false );
		if ( StringHelper.isNotEmpty( defaults.getSchema() )
				&& StringHelper.isEmpty( (String) annotation.valueOf( "schema" ) ) ) {
			annotation.setValue( "schema", defaults.getSchema() );
		}
		buildUniqueConstraints( annotation, subelement );
		buildIndex( annotation, subelement );
		annotation.setValue( "joinColumns", getJoinColumns( subelement, false ) );
		annotation.setValue( "inverseJoinColumns", getJoinColumns( subelement, true ) );
		return AnnotationFactory.create( annotation );
	}
