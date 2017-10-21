	private QueryBuilder commonQueryPart(String versionsMiddleEntityName) {
		// SELECT ee FROM middleEntity ee
		final QueryBuilder qb = new QueryBuilder( versionsMiddleEntityName, MIDDLE_ENTITY_ALIAS );
		qb.addProjection( null, MIDDLE_ENTITY_ALIAS, null, false );
		// WHERE
		// ee.originalId.id_ref_ing = :id_ref_ing
		referencingIdData.getPrefixedMapper().addNamedIdEqualsToQuery(
				qb.getRootParameters(),
				verEntCfg.getOriginalIdPropName(),
				true
		);
		return qb;
	}
