	public final Type getIdentifierOrUniqueKeyType(Mapping factory) throws MappingException {
		if ( isReferenceToPrimaryKey() || uniqueKeyPropertyName == null ) {
			return getIdentifierType( factory );
		}
		else {
			Type type = factory.getReferencedPropertyType( getAssociatedEntityName(), uniqueKeyPropertyName );
			if ( type.isEntityType() ) {
				type = ( (EntityType) type ).getIdentifierOrUniqueKeyType( factory );
			}
			return type;
		}
	}
