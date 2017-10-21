	protected AbstractRelationQueryGenerator(
			AuditEntitiesConfiguration verEntCfg,
			MiddleIdData referencingIdData,
			boolean keyRevisionTypeInId,
			boolean elementRevisionTypeInId) {
		this.verEntCfg = verEntCfg;
		this.referencingIdData = referencingIdData;
		this.keyRevisionTypeInId = keyRevisionTypeInId;
		this.elementRevisionTypeInId = elementRevisionTypeInId;
	}
