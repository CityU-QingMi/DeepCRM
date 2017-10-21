	private void createValidDataRestrictions(
			GlobalConfiguration globalCfg, AuditStrategy auditStrategy,
			MiddleIdData referencedIdData, QueryBuilder qb, Parameters rootParameters) {
		final String revisionPropertyPath = verEntCfg.getRevisionNumberPath();
		// (selecting e entities at revision :revision)
		// --> based on auditStrategy (see above)
		auditStrategy.addEntityAtRevisionRestriction(
				globalCfg, qb, rootParameters, revisionPropertyPath,
				verEntCfg.getRevisionEndFieldName(), true, referencedIdData, revisionPropertyPath,
				verEntCfg.getOriginalIdPropName(), REFERENCED_ENTITY_ALIAS, REFERENCED_ENTITY_ALIAS_DEF_AUD_STR,
				true
		);
		// e.revision_type != DEL
		rootParameters.addWhereWithNamedParam( getElementRevisionTypePath(), false, "!=", DEL_REVISION_TYPE_PARAMETER );
	}
