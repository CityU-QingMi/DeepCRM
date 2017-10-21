	private void getCollectionTable(List<Annotation> annotationList, Element element, XMLContext.Default defaults) {
		Element subelement = element != null ? element.element( "collection-table" ) : null;
		if ( subelement != null ) {
			AnnotationDescriptor annotation = new AnnotationDescriptor( CollectionTable.class );
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
			JoinColumn[] joinColumns = getJoinColumns( subelement, false );
			if ( joinColumns.length > 0 ) {
				annotation.setValue( "joinColumns", joinColumns );
			}
			buildUniqueConstraints( annotation, subelement );
			buildIndex( annotation, subelement );
			annotationList.add( AnnotationFactory.create( annotation ) );
		}
	}
