	private MapKeyJoinColumn[] getMapKeyJoinColumns(Element element) {
		List<Element> subelements = element != null ? element.elements( "map-key-join-column" ) : null;
		List<MapKeyJoinColumn> joinColumns = new ArrayList<MapKeyJoinColumn>();
		if ( subelements != null ) {
			for ( Element subelement : subelements ) {
				AnnotationDescriptor column = new AnnotationDescriptor( MapKeyJoinColumn.class );
				copyStringAttribute( column, subelement, "name", false );
				copyStringAttribute( column, subelement, "referenced-column-name", false );
				copyBooleanAttribute( column, subelement, "unique" );
				copyBooleanAttribute( column, subelement, "nullable" );
				copyBooleanAttribute( column, subelement, "insertable" );
				copyBooleanAttribute( column, subelement, "updatable" );
				copyStringAttribute( column, subelement, "column-definition", false );
				copyStringAttribute( column, subelement, "table", false );
				joinColumns.add( (MapKeyJoinColumn) AnnotationFactory.create( column ) );
			}
		}
		return joinColumns.toArray( new MapKeyJoinColumn[joinColumns.size()] );
	}
