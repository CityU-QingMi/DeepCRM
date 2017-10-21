	private void addJoins(
			Join join,
			JoinFragment joinFragment,
			Joinable joinable,
			String joinConditions) {
		final String rhsTableAlias = aliasResolutionContext.resolveSqlTableAliasFromQuerySpaceUid(
				join.getRightHandSide().getUid()
		);
		if ( StringHelper.isEmpty( rhsTableAlias ) ) {
			throw new IllegalStateException( "Join's RHS table alias cannot be empty" );
		}

		final String lhsTableAlias = aliasResolutionContext.resolveSqlTableAliasFromQuerySpaceUid(
				join.getLeftHandSide().getUid()
		);
		if ( lhsTableAlias == null ) {
			throw new IllegalStateException( "QuerySpace with that UID was not yet registered in the AliasResolutionContext" );
		}

		String otherConditions = join.getAnyAdditionalJoinConditions( rhsTableAlias );
		if ( !StringHelper.isEmpty( otherConditions ) && !StringHelper.isEmpty( joinConditions ) ) {
			otherConditions += " and " + joinConditions;
		}
		else if ( !StringHelper.isEmpty( joinConditions ) ) {
			otherConditions = joinConditions;
		}

		// add join fragments from the collection table -> element entity table ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		final String additionalJoinConditions = resolveAdditionalJoinCondition(
				rhsTableAlias,
				otherConditions,
				joinable,
				getJoinedAssociationTypeOrNull( join )
		);

		joinFragment.addJoin(
				joinable.getTableName(),
				rhsTableAlias,
				join.resolveAliasedLeftHandSideJoinConditionColumns( lhsTableAlias ),
				join.resolveNonAliasedRightHandSideJoinConditionColumns(),
				join.isRightHandSideRequired() ? JoinType.INNER_JOIN : JoinType.LEFT_OUTER_JOIN,
				additionalJoinConditions
		);
		joinFragment.addJoins(
				joinable.fromJoinFragment( rhsTableAlias, false, true ),
				joinable.whereJoinFragment( rhsTableAlias, false, true )
		);
	}
