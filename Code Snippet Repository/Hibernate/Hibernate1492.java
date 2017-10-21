	private static void buildQueryHints(List<Element> elements, AnnotationDescriptor ann){
		List<QueryHint> queryHints = new ArrayList<QueryHint>( elements.size() );
		for ( Element hint : elements ) {
			AnnotationDescriptor hintDescriptor = new AnnotationDescriptor( QueryHint.class );
			String value = hint.attributeValue( "name" );
			if ( value == null ) {
				throw new AnnotationException( "<hint> without name. " + SCHEMA_VALIDATION );
			}
			hintDescriptor.setValue( "name", value );
			value = hint.attributeValue( "value" );
			if ( value == null ) {
				throw new AnnotationException( "<hint> without value. " + SCHEMA_VALIDATION );
			}
			hintDescriptor.setValue( "value", value );
			queryHints.add( (QueryHint) AnnotationFactory.create( hintDescriptor ) );
		}
		ann.setValue( "hints", queryHints.toArray( new QueryHint[queryHints.size()] ) );
	}
