	protected final Object getIdentifier(Object value, SharedSessionContractImplementor session) throws HibernateException {
		if ( isReferenceToPrimaryKey() || uniqueKeyPropertyName == null ) {
			return ForeignKeys.getEntityIdentifierIfNotUnsaved(
					getAssociatedEntityName(),
					value,
					session
			); //tolerates nulls
		}
		else if ( value == null ) {
			return null;
		}
		else {
			EntityPersister entityPersister = getAssociatedEntityPersister( session.getFactory() );
			Object propertyValue = entityPersister.getPropertyValue( value, uniqueKeyPropertyName );
			// We now have the value of the property-ref we reference.  However,
			// we need to dig a little deeper, as that property might also be
			// an entity type, in which case we need to resolve its identitifier
			Type type = entityPersister.getPropertyType( uniqueKeyPropertyName );
			if ( type.isEntityType() ) {
				propertyValue = ( (EntityType) type ).getIdentifier( propertyValue, session );
			}

			return propertyValue;
		}
	}
