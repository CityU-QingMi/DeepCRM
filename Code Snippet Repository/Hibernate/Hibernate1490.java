	private NamedNativeQueries getNamedNativeQueries(
			Element tree,
			XMLContext.Default defaults) {
		List<NamedNativeQuery> queries = (List<NamedNativeQuery>) buildNamedQueries( tree, true, defaults, classLoaderAccess );
		if ( defaults.canUseJavaAnnotations() ) {
			NamedNativeQuery annotation = getPhysicalAnnotation( NamedNativeQuery.class );
			addNamedNativeQueryIfNeeded( annotation, queries );
			NamedNativeQueries annotations = getPhysicalAnnotation( NamedNativeQueries.class );
			if ( annotations != null ) {
				for ( NamedNativeQuery current : annotations.value() ) {
					addNamedNativeQueryIfNeeded( current, queries );
				}
			}
		}
		if ( queries.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( NamedNativeQueries.class );
			ad.setValue( "value", queries.toArray( new NamedNativeQuery[queries.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
