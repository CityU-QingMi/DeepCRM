	private EntityLoader createUniqueKeyLoader(
			Type uniqueKeyType,
			String[] columns,
			LoadQueryInfluencers loadQueryInfluencers) {
		if ( uniqueKeyType.isEntityType() ) {
			String className = ( (EntityType) uniqueKeyType ).getAssociatedEntityName();
			uniqueKeyType = getFactory().getMetamodel().entityPersister( className ).getIdentifierType();
		}
		return new EntityLoader(
				this,
				columns,
				uniqueKeyType,
				1,
				LockMode.NONE,
				getFactory(),
				loadQueryInfluencers
		);
	}
