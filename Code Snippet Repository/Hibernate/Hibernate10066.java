	public TwoEntityOneAuditedQueryGenerator(
			AuditEntitiesConfiguration verEntCfg, AuditStrategy auditStrategy,
			String versionsMiddleEntityName, MiddleIdData referencingIdData,
			MiddleIdData referencedIdData, boolean elementRevisionTypeInId,
			MiddleComponentData... componentData) {
		super( verEntCfg, referencingIdData, elementRevisionTypeInId, elementRevisionTypeInId );

/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/
		final QueryBuilder commonPart = commonQueryPart(
				referencedIdData,
				versionsMiddleEntityName,
				verEntCfg.getOriginalIdPropName()
		);
		final QueryBuilder validQuery = commonPart.deepCopy();
		final QueryBuilder removedQuery = commonPart.deepCopy();
		createValidDataRestrictions(
				auditStrategy, versionsMiddleEntityName, validQuery, validQuery.getRootParameters(), componentData
		);
		createValidAndRemovedDataRestrictions( auditStrategy, versionsMiddleEntityName, removedQuery, componentData );

		queryString = queryToString( validQuery );
		queryRemovedString = queryToString( removedQuery );
	}
