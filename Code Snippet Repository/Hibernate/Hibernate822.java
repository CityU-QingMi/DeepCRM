	private FilterSource[] buildFilterSources() {
		//todo for now, i think all EntityElement should support this.
		if ( JaxbHbmRootEntityType.class.isInstance( jaxbEntityMapping() ) ) {
			final JaxbHbmRootEntityType jaxbClassElement = (JaxbHbmRootEntityType) jaxbEntityMapping();
			final int size = jaxbClassElement.getFilter().size();
			if ( size == 0 ) {
				return NO_FILTER_SOURCES;
			}

			FilterSource[] results = new FilterSource[size];
			for ( int i = 0; i < size; i++ ) {
				JaxbHbmFilterType element = jaxbClassElement.getFilter().get( i );
				results[i] = new FilterSourceImpl( sourceMappingDocument(), element );
			}
			return results;
		}
		else {
			return NO_FILTER_SOURCES;
		}

	}
