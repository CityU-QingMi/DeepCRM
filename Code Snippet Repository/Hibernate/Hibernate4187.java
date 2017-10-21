	private int getSubclassPropertyTableNumber(String propertyName, String entityName) {
		// When there are duplicated property names in the subclasses
		// then propertyMapping.toType( propertyName ) may return an
		// incorrect Type. To ensure correct results, lookup the property type
		// using the concrete EntityPersister with the specified entityName
		// (since the concrete EntityPersister cannot have duplicated property names).
		final EntityPersister concreteEntityPersister;
		if ( getEntityName().equals( entityName ) ) {
			concreteEntityPersister = this;
		}
		else {
			concreteEntityPersister = getFactory().getMetamodel().entityPersister( entityName );
		}
		Type type = concreteEntityPersister.getPropertyType( propertyName );
		if ( type.isAssociationType() && ( (AssociationType) type ).useLHSPrimaryKey() ) {
			return 0;
		}
		final Integer tabnum = propertyTableNumbersByNameAndSubclass.get( entityName + '.' + propertyName );
		return tabnum == null ? 0 : tabnum;
	}
