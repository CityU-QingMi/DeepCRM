	public CommonCollectionMapperData(
			AuditEntitiesConfiguration verEntCfg, String versionsMiddleEntityName,
			PropertyData collectionReferencingPropertyData, MiddleIdData referencingIdData,
			RelationQueryGenerator queryGenerator) {
		this.verEntCfg = verEntCfg;
		this.versionsMiddleEntityName = versionsMiddleEntityName;
		this.collectionReferencingPropertyData = collectionReferencingPropertyData;
		this.referencingIdData = referencingIdData;
		this.queryGenerator = queryGenerator;
	}
