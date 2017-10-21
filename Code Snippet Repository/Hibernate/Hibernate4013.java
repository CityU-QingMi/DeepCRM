	@SuppressWarnings("")
	private <X, Y, E, K> AttributeImplementor<X, Y> buildPluralAttribute(PluralAttributeMetadata<X, Y, E> attributeMetadata) {
		final Type<E> elementType = getMetaModelType( attributeMetadata.getElementValueContext() );
		if ( java.util.Map.class.isAssignableFrom( attributeMetadata.getJavaType() ) ) {
			final Type<K> keyType = getMetaModelType( attributeMetadata.getMapKeyValueContext() );
			return PluralAttributeImpl.create(
					attributeMetadata.getOwnerType(),
					elementType,
					attributeMetadata.getJavaType(),
					keyType
			)
					.member( attributeMetadata.getMember() )
					.property( attributeMetadata.getPropertyMapping() )
					.persistentAttributeType( attributeMetadata.getPersistentAttributeType() )
					.build();
		}
		return PluralAttributeImpl.create(
				attributeMetadata.getOwnerType(),
				elementType,
				attributeMetadata.getJavaType(),
				null
		)
				.member( attributeMetadata.getMember() )
				.property( attributeMetadata.getPropertyMapping() )
				.persistentAttributeType( attributeMetadata.getPersistentAttributeType() )
				.build();
	}
