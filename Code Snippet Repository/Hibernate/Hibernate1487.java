	private NamedStoredProcedureQueries getNamedStoredProcedureQueries(Element tree, XMLContext.Default defaults) {
		List<NamedStoredProcedureQuery> queries = buildNamedStoreProcedureQueries( tree, defaults, classLoaderAccess );
		if ( defaults.canUseJavaAnnotations() ) {
			NamedStoredProcedureQuery annotation = getPhysicalAnnotation( NamedStoredProcedureQuery.class );
			addNamedStoredProcedureQueryIfNeeded( annotation, queries );
			NamedStoredProcedureQueries annotations = getPhysicalAnnotation( NamedStoredProcedureQueries.class );
			if ( annotations != null ) {
				for ( NamedStoredProcedureQuery current : annotations.value() ) {
					addNamedStoredProcedureQueryIfNeeded( current, queries );
				}
			}
		}
		if ( queries.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( NamedStoredProcedureQueries.class );
			ad.setValue( "value", queries.toArray( new NamedStoredProcedureQuery[queries.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
