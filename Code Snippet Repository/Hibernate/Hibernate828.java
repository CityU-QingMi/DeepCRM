	private static FilterSource[] buildFilterSources(
			MappingDocument mappingDocument,
			PluralAttributeInfo pluralAttributeElement) {
		final int size = pluralAttributeElement.getFilter().size();
		if ( size == 0 ) {
			return null;
		}

		FilterSource[] results = new FilterSource[size];
		for ( int i = 0; i < size; i++ ) {
			JaxbHbmFilterType element = pluralAttributeElement.getFilter().get( i );
			results[i] = new FilterSourceImpl( mappingDocument, element );
		}
		return results;

	}
