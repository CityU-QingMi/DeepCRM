	public AttributeConverterDefinition(AttributeConverter attributeConverter, boolean autoApply) {
		this.attributeConverter = attributeConverter;
		this.autoApply = autoApply;

		final Class attributeConverterClass = attributeConverter.getClass();
		final ParameterizedType attributeConverterSignature = extractAttributeConverterParameterizedType( attributeConverterClass );
		if ( attributeConverterSignature == null ) {
			throw new AssertionFailure(
					"Could not extract ParameterizedType representation of AttributeConverter definition " +
							"from AttributeConverter implementation class [" + attributeConverterClass.getName() + "]"
			);
		}

		if ( attributeConverterSignature.getActualTypeArguments().length < 2 ) {
			throw new AnnotationException(
					"AttributeConverter [" + attributeConverterClass.getName()
							+ "] did not retain parameterized type information"
			);
		}

		if ( attributeConverterSignature.getActualTypeArguments().length > 2 ) {
			throw new AnnotationException(
					"AttributeConverter [" + attributeConverterClass.getName()
							+ "] specified more than 2 parameterized types"
			);
		}
		entityAttributeType = extractClass( attributeConverterSignature.getActualTypeArguments()[0] );
		if ( entityAttributeType == null ) {
			throw new AnnotationException(
					"Could not determine 'entity attribute' type from given AttributeConverter [" +
							attributeConverterClass.getName() + "]"
			);
		}

		databaseColumnType = extractClass(attributeConverterSignature.getActualTypeArguments()[1]);
		if ( databaseColumnType == null ) {
			throw new AnnotationException(
					"Could not determine 'database column' type from given AttributeConverter [" +
							attributeConverterClass.getName() + "]"
			);
		}
	}
