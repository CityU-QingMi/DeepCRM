	@SuppressWarnings({""})
	public <X, Y> SingularAttributeImpl<X, Y> buildIdAttribute(
			AbstractIdentifiableType<X> ownerType,
			Property property) {
		LOG.trace( "Building identifier attribute [" + ownerType.getTypeName() + "." + property.getName() + "]" );
		final AttributeContext<X> attributeContext = wrap( ownerType, property );
		final SingularAttributeMetadata<X, Y> attributeMetadata =
				(SingularAttributeMetadata<X, Y>) determineAttributeMetadata(
						attributeContext,
						identifierMemberResolver
				);
		final Type<Y> metaModelType = getMetaModelType( attributeMetadata.getValueContext() );
		return new SingularAttributeImpl.Identifier(
				property.getName(),
				attributeMetadata.getJavaType(),
				ownerType,
				attributeMetadata.getMember(),
				metaModelType,
				attributeMetadata.getPersistentAttributeType()
		);
	}
