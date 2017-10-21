	private void handleCompositeJoin(Join join, JoinFragment joinFragment) {
		final String leftHandSideUid = join.getLeftHandSide().getUid();
		final String rightHandSideUid = join.getRightHandSide().getUid();

		final String leftHandSideTableAlias = aliasResolutionContext.resolveSqlTableAliasFromQuerySpaceUid( leftHandSideUid );
		if ( leftHandSideTableAlias == null ) {
			throw new IllegalStateException(
					"QuerySpace with that UID was not yet registered in the AliasResolutionContext"
			);
		}

		aliasResolutionContext.registerCompositeQuerySpaceUidResolution( rightHandSideUid, leftHandSideTableAlias );
	}
