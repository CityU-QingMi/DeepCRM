	private NamedEntityGraphs getNamedEntityGraphs(Element tree, XMLContext.Default defaults) {
		List<NamedEntityGraph> queries = buildNamedEntityGraph( tree, defaults, classLoaderAccess );
		if ( defaults.canUseJavaAnnotations() ) {
			NamedEntityGraph annotation = getPhysicalAnnotation( NamedEntityGraph.class );
			addNamedEntityGraphIfNeeded( annotation, queries );
			NamedEntityGraphs annotations = getPhysicalAnnotation( NamedEntityGraphs.class );
			if ( annotations != null ) {
				for ( NamedEntityGraph current : annotations.value() ) {
					addNamedEntityGraphIfNeeded( current, queries );
				}
			}
		}
		if ( queries.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( NamedEntityGraphs.class );
			ad.setValue( "value", queries.toArray( new NamedEntityGraph[queries.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
