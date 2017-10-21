	public void addWhereOrNullRestriction(String left, boolean addAliasLeft, String op, String right, boolean addAliasRight) {
		// apply the normal addWhere predicate
		final Parameters sub1 = addSubParameters( "or" );
		sub1.addWhere( left, addAliasLeft, op, right, addAliasRight );

		// apply the is null predicate for both join properties
		final Parameters sub2 = sub1.addSubParameters( "and" );
		sub2.addNullRestriction( left, false );
		sub2.addNullRestriction( right, false );
	}
