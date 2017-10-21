	public void addEntityAtRevisionRestriction(
			GlobalConfiguration globalCfg,
			QueryBuilder rootQueryBuilder,
			Parameters parameters,
			String revisionProperty,
			String revisionEndProperty,
			boolean addAlias,
			MiddleIdData idData,
			String revisionPropertyPath,
			String originalIdPropertyName,
			String alias1,
			String alias2,
			boolean inclusive) {
		// create a subquery builder
		// SELECT max(e.revision) FROM versionsReferencedEntity e2
		QueryBuilder maxERevQb = rootQueryBuilder.newSubQueryBuilder( idData.getAuditEntityName(), alias2 );
		maxERevQb.addProjection( "max", alias2, revisionPropertyPath, false );
		// WHERE
		Parameters maxERevQbParameters = maxERevQb.getRootParameters();
		// e2.revision <= :revision
		maxERevQbParameters.addWhereWithNamedParam( revisionPropertyPath, inclusive ? "<=" : "<", REVISION_PARAMETER );
		// e2.id_ref_ed = e.id_ref_ed
		idData.getOriginalMapper().addIdsEqualToQuery(
				maxERevQbParameters,
				alias1 + "." + originalIdPropertyName, alias2 + "." + originalIdPropertyName
		);

		// add subquery to rootParameters
		String subqueryOperator = globalCfg.getCorrelatedSubqueryOperator();
		parameters.addWhere( revisionProperty, addAlias, subqueryOperator, maxERevQb );
	}
