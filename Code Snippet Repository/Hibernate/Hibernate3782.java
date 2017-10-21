	private EntityReferenceAliases generateEntityReferenceAliases(
			String uid,
			String tableAlias,
			EntityPersister entityPersister) {
		final EntityReferenceAliasesImpl entityReferenceAliases = new EntityReferenceAliasesImpl(
				tableAlias,
				createEntityAliases( entityPersister )
		);
		registerQuerySpaceAliases( uid, entityReferenceAliases );
		return entityReferenceAliases;
	}
