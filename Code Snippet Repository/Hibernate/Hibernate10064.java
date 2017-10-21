	private QueryBuilder commonQueryPart(
			MiddleIdData referencedIdData, MiddleIdData indexIdData,
			String versionsMiddleEntityName, String originalIdPropertyName) {
		final String eeOriginalIdPropertyPath = MIDDLE_ENTITY_ALIAS + "." + originalIdPropertyName;
		// SELECT new list(ee) FROM middleEntity ee
		final QueryBuilder qb = new QueryBuilder( versionsMiddleEntityName, MIDDLE_ENTITY_ALIAS );
		qb.addFrom( referencedIdData.getAuditEntityName(), REFERENCED_ENTITY_ALIAS, false );
		qb.addFrom( indexIdData.getAuditEntityName(), INDEX_ENTITY_ALIAS, false );
		qb.addProjection(
				"new list", MIDDLE_ENTITY_ALIAS + ", " + REFERENCED_ENTITY_ALIAS + ", " + INDEX_ENTITY_ALIAS,
				null, false
		);
		// WHERE
		final Parameters rootParameters = qb.getRootParameters();
		// ee.id_ref_ed = e.id_ref_ed
		referencedIdData.getPrefixedMapper().addIdsEqualToQuery(
				rootParameters, eeOriginalIdPropertyPath, referencedIdData.getOriginalMapper(),
				REFERENCED_ENTITY_ALIAS + "." + originalIdPropertyName
		);
		// ee.id_ref_ind = f.id_ref_ind
		indexIdData.getPrefixedMapper().addIdsEqualToQuery(
				rootParameters, eeOriginalIdPropertyPath, indexIdData.getOriginalMapper(),
				INDEX_ENTITY_ALIAS + "." + originalIdPropertyName
		);
		// ee.originalId.id_ref_ing = :id_ref_ing
		referencingIdData.getPrefixedMapper().addNamedIdEqualsToQuery( rootParameters, originalIdPropertyName, true );
		return qb;
	}
