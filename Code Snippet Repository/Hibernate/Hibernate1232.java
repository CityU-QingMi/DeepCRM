	public AttributeConversionInfo(
			Class<? extends AttributeConverter> converterClass,
			boolean conversionDisabled,
			String attributeName,
			XAnnotatedElement source) {
		this.converterClass = converterClass;
		this.conversionDisabled = conversionDisabled;
		this.attributeName = attributeName;
		this.source = source;
	}
