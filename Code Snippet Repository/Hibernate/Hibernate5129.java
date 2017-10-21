	public SpecialOneToOneType(
			TypeFactory.TypeScope scope,
			String referencedEntityName,
			ForeignKeyDirection foreignKeyType,
			boolean referenceToPrimaryKey, 
			String uniqueKeyPropertyName,
			boolean lazy,
			boolean unwrapProxy,
			String entityName,
			String propertyName) {
		super(
				scope,
				referencedEntityName, 
				foreignKeyType,
				referenceToPrimaryKey, 
				uniqueKeyPropertyName, 
				lazy,
				unwrapProxy,
				entityName, 
				propertyName
			);
	}
