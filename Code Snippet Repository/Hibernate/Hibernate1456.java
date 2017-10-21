	private void applyXmlDefinedConverts(
			Element containingElement,
			XMLContext.Default defaults,
			String attributeNamePrefix,
			Map<String,Convert> convertAnnotationsMap) {
		final List<Element> convertElements = containingElement.elements( "convert" );
		for ( Element convertElement : convertElements ) {
			final AnnotationDescriptor convertAnnotationDescriptor = new AnnotationDescriptor( Convert.class );
			copyStringAttribute( convertAnnotationDescriptor, convertElement, "attribute-name", false );
			copyBooleanAttribute( convertAnnotationDescriptor, convertElement, "disable-conversion" );

			final Attribute converterClassAttr = convertElement.attribute( "converter" );
			if ( converterClassAttr != null ) {
				final String converterClassName = XMLContext.buildSafeClassName(
						converterClassAttr.getValue(),
						defaults
				);
				try {
					final Class converterClass = classLoaderAccess.classForName( converterClassName );
					convertAnnotationDescriptor.setValue( "converter", converterClass );
				}
				catch (ClassLoadingException e) {
					throw new AnnotationException( "Unable to find specified converter class id-class: " + converterClassName, e );
				}
			}
			final Convert convertAnnotation = AnnotationFactory.create( convertAnnotationDescriptor );
			final String qualifiedAttributeName = qualifyConverterAttributeName(
					attributeNamePrefix,
					convertAnnotation.attributeName()
			);
			convertAnnotationsMap.put( qualifiedAttributeName, convertAnnotation );
		}

	}
