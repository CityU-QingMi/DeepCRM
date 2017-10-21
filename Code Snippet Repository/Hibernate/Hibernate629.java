	private AttributeConverterDescriptorImpl(
			AttributeConverter attributeConverter,
			boolean autoApply,
			ResolvedType domainType,
			ResolvedType jdbcType) {
		this.attributeConverter = attributeConverter;
		this.autoApply = autoApply;
		this.domainType = domainType;
		this.jdbcType = jdbcType;
	}
