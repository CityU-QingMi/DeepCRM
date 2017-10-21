	private Converts getConverts(Element tree, XMLContext.Default defaults) {
		// NOTE : we use a map here to make sure that an xml and annotation referring to the same attribute
		// properly overrides.  Bit sparse, but easy...
		final Map<String,Convert> convertAnnotationsMap = new HashMap<String, Convert>();

		if ( tree != null ) {
			applyXmlDefinedConverts( tree, defaults, null, convertAnnotationsMap );
		}

		// NOTE : per section 12.2.3.16 of the spec <convert/> is additive, although only if "metadata-complete" is not
		// specified in the XML

		if ( defaults.canUseJavaAnnotations() ) {
			applyPhysicalConvertAnnotations( null, convertAnnotationsMap );
		}

		if ( !convertAnnotationsMap.isEmpty() ) {
			final AnnotationDescriptor groupingDescriptor = new AnnotationDescriptor( Converts.class );
			groupingDescriptor.setValue( "value", convertAnnotationsMap.values().toArray( new Convert[convertAnnotationsMap.size()]) );
			return AnnotationFactory.create( groupingDescriptor );
		}

		return null;
	}
