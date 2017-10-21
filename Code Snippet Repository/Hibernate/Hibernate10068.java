	private void createValidDataRestrictions(
			AuditStrategy auditStrategy, String versionsMiddleEntityName, QueryBuilder qb,
			Parameters rootParameters, MiddleComponentData... componentData) {
		final String revisionPropertyPath = verEntCfg.getRevisionNumberPath();
		final String originalIdPropertyName = verEntCfg.getOriginalIdPropName();
		final String eeOriginalIdPropertyPath = MIDDLE_ENTITY_ALIAS + "." + originalIdPropertyName;
		// (with ee association at revision :revision)
		// --> based on auditStrategy (see above)
		auditStrategy.addAssociationAtRevisionRestriction(
				qb, rootParameters, revisionPropertyPath, verEntCfg.getRevisionEndFieldName(), true,
				referencingIdData, versionsMiddleEntityName, eeOriginalIdPropertyPath, revisionPropertyPath,
				originalIdPropertyName, MIDDLE_ENTITY_ALIAS, true, componentData
		);
		// ee.revision_type != DEL
		rootParameters.addWhereWithNamedParam( getElementRevisionTypePath(), "!=", DEL_REVISION_TYPE_PARAMETER );
	}
