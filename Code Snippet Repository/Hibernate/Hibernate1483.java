	private NamedQueries getNamedQueries(Element tree, XMLContext.Default defaults) {
		//TODO avoid the Proxy Creation (@NamedQueries) when possible
		List<NamedQuery> queries = (List<NamedQuery>) buildNamedQueries( tree, false, defaults, classLoaderAccess );
		if ( defaults.canUseJavaAnnotations() ) {
			NamedQuery annotation = getPhysicalAnnotation( NamedQuery.class );
			addNamedQueryIfNeeded( annotation, queries );
			NamedQueries annotations = getPhysicalAnnotation( NamedQueries.class );
			if ( annotations != null ) {
				for ( NamedQuery current : annotations.value() ) {
					addNamedQueryIfNeeded( current, queries );
				}
			}
		}
		if ( queries.size() > 0 ) {
			AnnotationDescriptor ad = new AnnotationDescriptor( NamedQueries.class );
			ad.setValue( "value", queries.toArray( new NamedQuery[queries.size()] ) );
			return AnnotationFactory.create( ad );
		}
		else {
			return null;
		}
	}
