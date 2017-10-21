	private void getMapKeyColumn(List<Annotation> annotationList, Element element) {
		Element subelement = element != null ? element.element( "map-key-column" ) : null;
		if ( subelement != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( MapKeyColumn.class );
			copyStringAttribute( ad, subelement, "name", false );
			copyBooleanAttribute( ad, subelement, "unique" );
			copyBooleanAttribute( ad, subelement, "nullable" );
			copyBooleanAttribute( ad, subelement, "insertable" );
			copyBooleanAttribute( ad, subelement, "updatable" );
			copyStringAttribute( ad, subelement, "column-definition", false );
			copyStringAttribute( ad, subelement, "table", false );
			copyIntegerAttribute( ad, subelement, "length" );
			copyIntegerAttribute( ad, subelement, "precision" );
			copyIntegerAttribute( ad, subelement, "scale" );
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}
