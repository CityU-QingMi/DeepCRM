	private Annotation getConvertsForAttribute(List<Element> elementsForProperty, XMLContext.Default defaults) {
		// NOTE : we use a map here to make sure that an xml and annotation referring to the same attribute
		// properly overrides.  Very sparse map, yes, but easy setup.
		// todo : revisit this
		// although bear in mind that this code is no longer used in 5.0...

		final Map<String,Convert> convertAnnotationsMap = new HashMap<String, Convert>();

		for ( Element element : elementsForProperty ) {
			final boolean isBasic = "basic".equals( element.getName() );
			final boolean isEmbedded = "embedded".equals( element.getName() );

			// todo : can be collections too

			final boolean canHaveConverts = isBasic || isEmbedded;

			if ( !canHaveConverts ) {
				continue;
			}

			final String attributeNamePrefix = isBasic ? null : propertyName;
			applyXmlDefinedConverts( element, defaults, attributeNamePrefix, convertAnnotationsMap );
		}

		// NOTE : per section 12.2.3.16 of the spec <convert/> is additive, although only if "metadata-complete" is not
		// specified in the XML

		if ( defaults.canUseJavaAnnotations() ) {
			// todo : note sure how to best handle attributeNamePrefix here
			applyPhysicalConvertAnnotations( propertyName, convertAnnotationsMap );
		}

		if ( !convertAnnotationsMap.isEmpty() ) {
			final AnnotationDescriptor groupingDescriptor = new AnnotationDescriptor( Converts.class );
			groupingDescriptor.setValue( "value", convertAnnotationsMap.values().toArray( new Convert[convertAnnotationsMap.size()]) );
			return AnnotationFactory.create( groupingDescriptor );
		}

		return null;
	}
