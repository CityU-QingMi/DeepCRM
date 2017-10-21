	private <X> void applyIdMetadata(MappedSuperclass mappingType, MappedSuperclassTypeImpl<X> jpaMappingType) {
		if ( mappingType.hasIdentifierProperty() ) {
			final Property declaredIdentifierProperty = mappingType.getDeclaredIdentifierProperty();
			if ( declaredIdentifierProperty != null ) {
				jpaMappingType.getBuilder().applyIdAttribute(
						attributeFactory.buildIdAttribute( jpaMappingType, declaredIdentifierProperty )
				);
			}
		}
		//an MappedSuperclass can have no identifier if the id is set below in the hierarchy
		else if ( mappingType.getIdentifierMapper() != null ) {
			@SuppressWarnings("unchecked")
			Iterator<Property> propertyIterator = mappingType.getIdentifierMapper().getPropertyIterator();
			Set<SingularAttribute<? super X, ?>> attributes = buildIdClassAttributes(
					jpaMappingType,
					propertyIterator
			);
			jpaMappingType.getBuilder().applyIdClassAttributes( attributes );
		}
	}
