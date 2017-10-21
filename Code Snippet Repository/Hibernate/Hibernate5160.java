	@SuppressWarnings("")
	public AttributeConverterTypeAdapter(
			String name,
			String description,
			AttributeConverter<? extends T,?> attributeConverter,
			SqlTypeDescriptor sqlTypeDescriptorAdapter,
			Class modelType,
			Class jdbcType,
			JavaTypeDescriptor<T> entityAttributeJavaTypeDescriptor) {
		super( sqlTypeDescriptorAdapter, entityAttributeJavaTypeDescriptor );
		this.name = name;
		this.description = description;
		this.modelType = modelType;
		this.jdbcType = jdbcType;
		this.attributeConverter = attributeConverter;

		this.mutabilityPlan =
				entityAttributeJavaTypeDescriptor.getMutabilityPlan().isMutable() ?
						new AttributeConverterMutabilityPlanImpl<T>( attributeConverter ) :
						ImmutableMutabilityPlan.INSTANCE;

		log.debug( "Created AttributeConverterTypeAdapter -> " + name );
	}
