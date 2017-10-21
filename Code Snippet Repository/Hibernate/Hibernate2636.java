	private boolean isReferenceToPrimaryKey(String propertyName, EntityType owningType) {
		EntityPersister persister = getSessionFactoryHelper()
				.getFactory()
				.getEntityPersister( owningType.getAssociatedEntityName() );
		if ( persister.getEntityMetamodel().hasNonIdentifierPropertyNamedId() ) {
			// only the identifier property field name can be a reference to the associated entity's PK...
			return propertyName.equals( persister.getIdentifierPropertyName() ) && owningType.isReferenceToPrimaryKey();
		}
		// here, we have two possibilities:
		// 1) the property-name matches the explicitly identifier property name
		// 2) the property-name matches the implicit 'id' property name
		// the referenced node text is the special 'id'
		if ( EntityPersister.ENTITY_ID.equals( propertyName ) ) {
			return owningType.isReferenceToPrimaryKey();
		}
		String keyPropertyName = getSessionFactoryHelper().getIdentifierOrUniqueKeyPropertyName( owningType );
		return keyPropertyName != null && keyPropertyName.equals( propertyName ) && owningType.isReferenceToPrimaryKey();
	}
