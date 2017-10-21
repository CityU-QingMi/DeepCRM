	@Override
	public String[] resolveNonAliasedRightHandSideJoinConditionColumns() {
		// for composite joins (joins whose rhs is a composite) we'd have no columns here.
		// processing of joins tries to root out all composite joins, so the expectation
		// is that this method would never be called on them
		if ( rhsColumnNames == null ) {
			throw new IllegalStateException(
					"rhsColumnNames were null.  Generally that indicates a composite join, in which case calls to " +
							"resolveAliasedLeftHandSideJoinConditionColumns are not allowed"
			);
		}
		return rhsColumnNames;
	}
