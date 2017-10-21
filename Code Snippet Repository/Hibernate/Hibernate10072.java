	private void createValidAndRemovedDataRestrictions(
			GlobalConfiguration globalCfg, AuditStrategy auditStrategy,
			MiddleIdData referencedIdData, String versionsMiddleEntityName,
			QueryBuilder remQb, MiddleComponentData... componentData) {
		final Parameters disjoint = remQb.getRootParameters().addSubParameters( "or" );
		// Restrictions to match all valid rows.
		final Parameters valid = disjoint.addSubParameters( "and" );
		// Restrictions to match all rows deleted at exactly given revision.
		final Parameters removed = disjoint.addSubParameters( "and" );
		final String revisionPropertyPath = verEntCfg.getRevisionNumberPath();
		final String revisionTypePropName = getElementRevisionTypePath();
		// Excluding current revision, because we need to match data valid at the previous one.
		createValidDataRestrictions(
				globalCfg,
				auditStrategy,
				referencedIdData,
				versionsMiddleEntityName,
				remQb,
				valid,
				false,
				componentData
		);
		// ee.revision = :revision
		removed.addWhereWithNamedParam( revisionPropertyPath, "=", REVISION_PARAMETER );
		// e.revision = :revision
		removed.addWhereWithNamedParam(
				REFERENCED_ENTITY_ALIAS + "." + revisionPropertyPath,
				false,
				"=",
				REVISION_PARAMETER
		);
		// ee.revision_type = DEL
		removed.addWhereWithNamedParam( revisionTypePropName, "=", DEL_REVISION_TYPE_PARAMETER );
		// e.revision_type = DEL
		removed.addWhereWithNamedParam(
				REFERENCED_ENTITY_ALIAS + "." + getKeyRevisionTypePath(),
				false,
				"=",
				DEL_REVISION_TYPE_PARAMETER
		);
	}
