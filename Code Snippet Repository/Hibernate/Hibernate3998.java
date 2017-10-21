	@SuppressWarnings({""})
	public <X, Y> AttributeImplementor<X, Y> buildAttribute(AbstractManagedType<X> ownerType, Property property) {
		if ( property.isSynthetic() ) {
			// hide synthetic/virtual properties (fabricated by Hibernate) from the JPA metamodel.
			LOG.tracef( "Skipping synthetic property %s(%s)", ownerType.getTypeName(), property.getName() );
			return null;
		}
		LOG.trace( "Building attribute [" + ownerType.getTypeName() + "." + property.getName() + "]" );
		final AttributeContext<X> attributeContext = wrap( ownerType, property );
		final AttributeMetadata<X, Y> attributeMetadata =
				determineAttributeMetadata( attributeContext, normalMemberResolver );
		if ( attributeMetadata == null ) {
			return null;
		}
		if ( attributeMetadata.isPlural() ) {
			return buildPluralAttribute( (PluralAttributeMetadata) attributeMetadata );
		}
		final SingularAttributeMetadata<X, Y> singularAttributeMetadata = (SingularAttributeMetadata<X, Y>) attributeMetadata;
		final Type<Y> metaModelType = getMetaModelType( singularAttributeMetadata.getValueContext() );
		return new SingularAttributeImpl<X, Y>(
				attributeMetadata.getName(),
				attributeMetadata.getJavaType(),
				ownerType,
				attributeMetadata.getMember(),
				false,
				false,
				property.isOptional(),
				metaModelType,
				attributeMetadata.getPersistentAttributeType()
		);
	}
