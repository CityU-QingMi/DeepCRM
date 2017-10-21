	private void applyPhysicalConvertAnnotations(
			String attributeNamePrefix,
			Map<String, Convert> convertAnnotationsMap) {
		final Convert physicalAnnotation = getPhysicalAnnotation( Convert.class );
		if ( physicalAnnotation != null ) {
			// only add if no XML element named a converter for this attribute
			final String qualifiedAttributeName = qualifyConverterAttributeName( attributeNamePrefix, physicalAnnotation.attributeName() );
			if ( ! convertAnnotationsMap.containsKey( qualifiedAttributeName ) ) {
				convertAnnotationsMap.put( qualifiedAttributeName, physicalAnnotation );
			}
		}
		final Converts physicalGroupingAnnotation = getPhysicalAnnotation( Converts.class );
		if ( physicalGroupingAnnotation != null ) {
			for ( Convert convertAnnotation : physicalGroupingAnnotation.value() ) {
				// again, only add if no XML element named a converter for this attribute
				final String qualifiedAttributeName = qualifyConverterAttributeName( attributeNamePrefix, convertAnnotation.attributeName() );
				if ( ! convertAnnotationsMap.containsKey( qualifiedAttributeName ) ) {
					convertAnnotationsMap.put( qualifiedAttributeName, convertAnnotation );
				}
			}
		}
	}
