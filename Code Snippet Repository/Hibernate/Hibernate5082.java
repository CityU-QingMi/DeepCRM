	protected EntityType(
			TypeFactory.TypeScope scope,
			String entityName,
			boolean referenceToPrimaryKey,
			String uniqueKeyPropertyName,
			boolean eager,
			boolean unwrapProxy) {
		this.scope = scope;
		this.associatedEntityName = entityName;
		this.uniqueKeyPropertyName = uniqueKeyPropertyName;
		this.eager = eager;
		this.unwrapProxy = unwrapProxy;
		this.referenceToPrimaryKey = referenceToPrimaryKey;
	}
