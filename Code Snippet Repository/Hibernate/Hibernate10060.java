	public OneEntityQueryGenerator(
			AuditEntitiesConfiguration verEntCfg, AuditStrategy auditStrategy,
			String versionsMiddleEntityName, MiddleIdData referencingIdData,
			boolean revisionTypeInId, MiddleComponentData... componentData) {
		super( verEntCfg, referencingIdData, revisionTypeInId, revisionTypeInId );

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
		final QueryBuilder commonPart = commonQueryPart( versionsMiddleEntityName );
		final QueryBuilder validQuery = commonPart.deepCopy();
		final QueryBuilder removedQuery = commonPart.deepCopy();
		createValidDataRestrictions(
				auditStrategy, versionsMiddleEntityName, validQuery, validQuery.getRootParameters(), true, componentData
		);
		createValidAndRemovedDataRestrictions( auditStrategy, versionsMiddleEntityName, removedQuery, componentData );

		queryString = queryToString( validQuery );
		queryRemovedString = queryToString( removedQuery );
	}
