	public static AttributeConverterDescriptor create(
			AttributeConverterDefinition definition,
			ClassmateContext classmateContext) {
		final AttributeConverter converter = definition.getAttributeConverter();
		final Class converterClass = converter.getClass();

		final ResolvedType converterType = classmateContext.getTypeResolver().resolve( converterClass );
		final List<ResolvedType> converterParamTypes = converterType.typeParametersFor( AttributeConverter.class );
		if ( converterParamTypes == null ) {
			throw new AnnotationException(
					"Could not extract type parameter information from AttributeConverter implementation ["
							+ converterClass.getName() + "]"
			);
		}
		else if ( converterParamTypes.size() != 2 ) {
			throw new AnnotationException(
					"Unexpected type parameter information for AttributeConverter implementation [" +
							converterClass.getName() + "]; expected 2 parameter types, but found " + converterParamTypes.size()
			);
		}

		return new AttributeConverterDescriptorImpl(
				converter,
				definition.isAutoApply(),
				converterParamTypes.get( 0 ),
				converterParamTypes.get( 1 )
		);
	}
