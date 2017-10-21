	@SuppressWarnings({""})
	public <X, Y> SingularAttributeImpl<X, Y> buildVersionAttribute(
			AbstractIdentifiableType<X> ownerType,
			Property property) {
		LOG.trace( "Building version attribute [ownerType.getTypeName()" + "." + "property.getName()]" );
		final AttributeContext<X> attributeContext = wrap( ownerType, property );
		final SingularAttributeMetadata<X, Y> attributeMetadata =
				(SingularAttributeMetadata<X, Y>) determineAttributeMetadata( attributeContext, versionMemberResolver );
		final Type<Y> metaModelType = getMetaModelType( attributeMetadata.getValueContext() );
		return new SingularAttributeImpl.Version(
				property.getName(),
				attributeMetadata.getJavaType(),
				ownerType,
				attributeMetadata.getMember(),
				metaModelType,
				attributeMetadata.getPersistentAttributeType()
		);
	}
