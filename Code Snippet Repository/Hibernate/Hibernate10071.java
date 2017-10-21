	private void createValidDataRestrictions(
			GlobalConfiguration globalCfg, AuditStrategy auditStrategy, MiddleIdData referencedIdData,
			String versionsMiddleEntityName, QueryBuilder qb, Parameters rootParameters,
			boolean inclusive, MiddleComponentData... componentData) {
		final String revisionPropertyPath = verEntCfg.getRevisionNumberPath();
		final String originalIdPropertyName = verEntCfg.getOriginalIdPropName();
		final String eeOriginalIdPropertyPath = MIDDLE_ENTITY_ALIAS + "." + originalIdPropertyName;
		final String revisionTypePropName = getElementRevisionTypePath();
		// (selecting e entities at revision :revision)
		// --> based on auditStrategy (see above)
		auditStrategy.addEntityAtRevisionRestriction(
				globalCfg,
				qb,
				rootParameters,
				REFERENCED_ENTITY_ALIAS + "." + revisionPropertyPath,
				REFERENCED_ENTITY_ALIAS + "." + verEntCfg.getRevisionEndFieldName(),
				false,
				referencedIdData,
				revisionPropertyPath,
				originalIdPropertyName,
				REFERENCED_ENTITY_ALIAS,
				REFERENCED_ENTITY_ALIAS_DEF_AUD_STR,
				true
		);
		// (with ee association at revision :revision)
		// --> based on auditStrategy (see above)
		auditStrategy.addAssociationAtRevisionRestriction(
				qb, rootParameters, revisionPropertyPath,
				verEntCfg.getRevisionEndFieldName(), true, referencingIdData, versionsMiddleEntityName,
				eeOriginalIdPropertyPath, revisionPropertyPath, originalIdPropertyName, MIDDLE_ENTITY_ALIAS,
				inclusive, componentData
		);
		// ee.revision_type != DEL
		rootParameters.addWhereWithNamedParam( revisionTypePropName, "!=", DEL_REVISION_TYPE_PARAMETER );
		// e.revision_type != DEL
		rootParameters.addWhereWithNamedParam(
				REFERENCED_ENTITY_ALIAS + "." + getKeyRevisionTypePath(),
				false,
				"!=",
				DEL_REVISION_TYPE_PARAMETER
		);
	}
