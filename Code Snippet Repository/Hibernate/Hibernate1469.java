	private Column getColumn(Element element, boolean isMandatory, Element current) {
		//Element subelement = element != null ? element.element( "column" ) : null;
		if ( element != null ) {
			AnnotationDescriptor column = new AnnotationDescriptor( Column.class );
			copyStringAttribute( column, element, "name", false );
			copyBooleanAttribute( column, element, "unique" );
			copyBooleanAttribute( column, element, "nullable" );
			copyBooleanAttribute( column, element, "insertable" );
			copyBooleanAttribute( column, element, "updatable" );
			copyStringAttribute( column, element, "column-definition", false );
			copyStringAttribute( column, element, "table", false );
			copyIntegerAttribute( column, element, "length" );
			copyIntegerAttribute( column, element, "precision" );
			copyIntegerAttribute( column, element, "scale" );
			return (Column) AnnotationFactory.create( column );
		}
		else {
			if ( isMandatory ) {
				throw new AnnotationException( current.getPath() + ".column is mandatory. " + SCHEMA_VALIDATION );
			}
			return null;
		}
	}
