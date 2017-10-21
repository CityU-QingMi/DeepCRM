	private Columns buildColumns(Element element) {
		List<Element> subelements = element.elements( "column" );
		List<Column> columns = new ArrayList<Column>( subelements.size() );
		for ( Element subelement : subelements ) {
			columns.add( getColumn( subelement, false, element ) );
		}
		if ( columns.size() > 0 ) {
			AnnotationDescriptor columnsDescr = new AnnotationDescriptor( Columns.class );
			columnsDescr.setValue( "columns", columns.toArray( new Column[columns.size()] ) );
			return AnnotationFactory.create( columnsDescr );
		}
		else {
			return null;
		}
	}
