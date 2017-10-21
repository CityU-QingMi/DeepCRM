	private void createValidAndRemovedDataRestrictions(
			AuditStrategy auditStrategy, String versionsMiddleEntityName,
			QueryBuilder remQb, MiddleComponentData... componentData) {
		final Parameters disjoint = remQb.getRootParameters().addSubParameters( "or" );
		// Restrictions to match all valid rows.
		final Parameters valid = disjoint.addSubParameters( "and" );
		// Restrictions to match all rows deleted at exactly given revision.
		final Parameters removed = disjoint.addSubParameters( "and" );
		createValidDataRestrictions( auditStrategy, versionsMiddleEntityName, remQb, valid, componentData );
		// ee.revision = :revision
		removed.addWhereWithNamedParam( verEntCfg.getRevisionNumberPath(), "=", REVISION_PARAMETER );
		// ee.revision_type = DEL
		removed.addWhereWithNamedParam( getElementRevisionTypePath(), "=", DEL_REVISION_TYPE_PARAMETER );
	}
