	public AttributeConverterDescriptorNonAutoApplicableImpl(AttributeConverter converter) {
		this.converter = converter;

		final Class attributeConverterClass = converter.getClass();
		final ParameterizedType attributeConverterSignature = extractAttributeConverterParameterizedType(
				attributeConverterClass
		);
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

		this.domainType = extractClass( attributeConverterSignature.getActualTypeArguments()[0] );
		if ( this.domainType == null ) {
			throw new AnnotationException(
					"Could not determine domain type from given AttributeConverter [" +
							attributeConverterClass.getName() + "]"
			);
		}

		this.jdbcType = extractClass(attributeConverterSignature.getActualTypeArguments()[1]);
		if ( this.jdbcType == null ) {
			throw new AnnotationException(
					"Could not determine JDBC type from given AttributeConverter [" +
							attributeConverterClass.getName() + "]"
			);
		}
	}
