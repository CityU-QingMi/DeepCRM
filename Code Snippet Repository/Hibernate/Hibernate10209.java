	public void addAssociationAtRevisionRestriction(
			QueryBuilder rootQueryBuilder,
			Parameters parameters,
			String revisionProperty,
			String revisionEndProperty,
			boolean addAlias,
			MiddleIdData referencingIdData,
			String versionsMiddleEntityName,
			String eeOriginalIdPropertyPath,
			String revisionPropertyPath,
			String originalIdPropertyName,
			String alias1,
			boolean inclusive,
			MiddleComponentData... componentDatas) {
		// SELECT max(ee2.revision) FROM middleEntity ee2
		QueryBuilder maxEeRevQb = rootQueryBuilder.newSubQueryBuilder(
				versionsMiddleEntityName,
				MIDDLE_ENTITY_ALIAS_DEF_AUD_STR
		);
		maxEeRevQb.addProjection( "max", MIDDLE_ENTITY_ALIAS_DEF_AUD_STR, revisionPropertyPath, false );
		// WHERE
		Parameters maxEeRevQbParameters = maxEeRevQb.getRootParameters();
		// ee2.revision <= :revision
		maxEeRevQbParameters.addWhereWithNamedParam( revisionPropertyPath, inclusive ? "<=" : "<", REVISION_PARAMETER );
		// ee2.originalId.* = ee.originalId.*
		String ee2OriginalIdPropertyPath = MIDDLE_ENTITY_ALIAS_DEF_AUD_STR + "." + originalIdPropertyName;
		referencingIdData.getPrefixedMapper().addIdsEqualToQuery(
				maxEeRevQbParameters,
				eeOriginalIdPropertyPath,
				ee2OriginalIdPropertyPath
		);
		for ( MiddleComponentData componentData : componentDatas ) {
			componentData.getComponentMapper().addMiddleEqualToQuery(
					maxEeRevQbParameters,
					eeOriginalIdPropertyPath,
					alias1,
					ee2OriginalIdPropertyPath,
					MIDDLE_ENTITY_ALIAS_DEF_AUD_STR
			);
		}

		// add subquery to rootParameters
		parameters.addWhere( revisionProperty, addAlias, "=", maxEeRevQb );
	}
