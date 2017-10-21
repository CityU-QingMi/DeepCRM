	private JoinColumn[] getJoinColumns(Element element, boolean isInverse) {
		List<Element> subelements = element != null ?
				element.elements( isInverse ? "inverse-join-column" : "join-column" ) :
				null;
		List<JoinColumn> joinColumns = new ArrayList<JoinColumn>();
		if ( subelements != null ) {
			for ( Element subelement : subelements ) {
				AnnotationDescriptor column = new AnnotationDescriptor( JoinColumn.class );
				copyStringAttribute( column, subelement, "name", false );
				copyStringAttribute( column, subelement, "referenced-column-name", false );
				copyBooleanAttribute( column, subelement, "unique" );
				copyBooleanAttribute( column, subelement, "nullable" );
				copyBooleanAttribute( column, subelement, "insertable" );
				copyBooleanAttribute( column, subelement, "updatable" );
				copyStringAttribute( column, subelement, "column-definition", false );
				copyStringAttribute( column, subelement, "table", false );
				joinColumns.add( (JoinColumn) AnnotationFactory.create( column ) );
			}
		}
		return joinColumns.toArray( new JoinColumn[joinColumns.size()] );
	}
