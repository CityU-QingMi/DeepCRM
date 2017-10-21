	protected boolean isJoinable(
			final JoinType joinType,
			final Set visitedAssociationKeys,
			final String lhsTable,
			final String[] lhsColumnNames,
			final AssociationType type,
			final int depth) {

		if ( joinType == JoinType.NONE ) {
			return false;
		}

		if ( joinType == JoinType.INNER_JOIN ) {
			return true;
		}

		Integer maxFetchDepth = getFactory().getSessionFactoryOptions().getMaximumFetchDepth();
		final boolean tooDeep = maxFetchDepth != null && depth >= maxFetchDepth;

		return !tooDeep && !isDuplicateAssociation( lhsTable, lhsColumnNames, type );
	}
