	private SecondaryTables getSecondaryTables(Element tree, XMLContext.Default defaults) {
		List<Element> elements = tree == null ?
				new ArrayList<Element>() :
				(List<Element>) tree.elements( "secondary-table" );
		List<SecondaryTable> secondaryTables = new ArrayList<SecondaryTable>( 3 );
		for ( Element element : elements ) {
			AnnotationDescriptor annotation = new AnnotationDescriptor( SecondaryTable.class );
			copyStringAttribute( annotation, element, "name", false );
			copyStringAttribute( annotation, element, "catalog", false );
			if ( StringHelper.isNotEmpty( defaults.getCatalog() )
					&& StringHelper.isEmpty( (String) annotation.valueOf( "catalog" ) ) ) {
				annotation.setValue( "catalog", defaults.getCatalog() );
			}
			copyStringAttribute( annotation, element, "schema", false );
			if ( StringHelper.isNotEmpty( defaults.getSchema() )
					&& StringHelper.isEmpty( (String) annotation.valueOf( "schema" ) ) ) {
				annotation.setValue( "schema", defaults.getSchema() );
			}
			buildUniqueConstraints( annotation, element );
			buildIndex( annotation, element );
			annotation.setValue( "pkJoinColumns", buildPrimaryKeyJoinColumns( element ) );
			secondaryTables.add( (SecondaryTable) AnnotationFactory.create( annotation ) );
		}
/**/
/**/
/**/
/**/
		if ( secondaryTables.size() == 0 && defaults.canUseJavaAnnotations() ) {
			SecondaryTable secTableAnn = getPhysicalAnnotation( SecondaryTable.class );
			overridesDefaultInSecondaryTable( secTableAnn, defaults, secondaryTables );
			SecondaryTables secTablesAnn = getPhysicalAnnotation( SecondaryTables.class );
			if ( secTablesAnn != null ) {
				for ( SecondaryTable table : secTablesAnn.value() ) {
					overridesDefaultInSecondaryTable( table, defaults, secondaryTables );
				}
			}
		}
		if ( secondaryTables.size() > 0 ) {
			AnnotationDescriptor descriptor = new AnnotationDescriptor( SecondaryTables.class );
			descriptor.setValue( "value", secondaryTables.toArray( new SecondaryTable[secondaryTables.size()] ) );
			return AnnotationFactory.create( descriptor );
		}
		else {
			return null;
		}
	}
