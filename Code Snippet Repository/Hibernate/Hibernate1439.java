	private void getOrderColumn(List<Annotation> annotationList, Element element) {
		Element subelement = element != null ? element.element( "order-column" ) : null;
		if ( subelement != null ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( OrderColumn.class );
			copyStringAttribute( ad, subelement, "name", false );
			copyBooleanAttribute( ad, subelement, "nullable" );
			copyBooleanAttribute( ad, subelement, "insertable" );
			copyBooleanAttribute( ad, subelement, "updatable" );
			copyStringAttribute( ad, subelement, "column-definition", false );
			annotationList.add( AnnotationFactory.create( ad ) );
		}
	}
