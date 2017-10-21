	private static void bindFilters(XAnnotatedElement annotatedElement, EntityBinder entityBinder) {

		Filters filtersAnn = annotatedElement.getAnnotation( Filters.class );
		if ( filtersAnn != null ) {
			for ( Filter filter : filtersAnn.value() ) {
				entityBinder.addFilter(filter);
			}
		}

		Filter filterAnn = annotatedElement.getAnnotation( Filter.class );
		if ( filterAnn != null ) {
			entityBinder.addFilter(filterAnn);
		}
	}
