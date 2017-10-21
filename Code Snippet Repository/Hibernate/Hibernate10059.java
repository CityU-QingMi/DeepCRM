	private void createValidAndRemovedDataRestrictions(
			GlobalConfiguration globalCfg, AuditStrategy auditStrategy,
			MiddleIdData referencedIdData, QueryBuilder remQb) {
		final Parameters disjoint = remQb.getRootParameters().addSubParameters( "or" );
		// Restrictions to match all valid rows.
		final Parameters valid = disjoint.addSubParameters( "and" );
		// Restrictions to match all rows deleted at exactly given revision.
		final Parameters removed = disjoint.addSubParameters( "and" );
		// Excluding current revision, because we need to match data valid at the previous one.
		createValidDataRestrictions( globalCfg, auditStrategy, referencedIdData, remQb, valid );
		// e.revision = :revision
		removed.addWhereWithNamedParam( verEntCfg.getRevisionNumberPath(), false, "=", REVISION_PARAMETER );
		// e.revision_type = DEL
		removed.addWhereWithNamedParam( getElementRevisionTypePath(), false, "=", DEL_REVISION_TYPE_PARAMETER );
	}
