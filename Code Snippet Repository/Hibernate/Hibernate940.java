	private FilterSource[] buildFilterSources() {
		final int size = jaxbManyToManyElement.getFilter().size();
		if ( size == 0 ) {
			return NO_FILTER_SOURCES;
		}

		FilterSource[] results = new FilterSource[size];
		for ( int i = 0; i < size; i++ ) {
			JaxbHbmFilterType element = jaxbManyToManyElement.getFilter().get( i );
			results[i] = new FilterSourceImpl( sourceMappingDocument(), element );
		}
		return results;
	}
