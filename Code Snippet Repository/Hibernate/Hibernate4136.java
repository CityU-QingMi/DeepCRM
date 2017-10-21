	protected JoinType determineSubclassTableJoinType(
			int subclassTableNumber,
			boolean canInnerJoin,
			boolean includeSubclasses,
			Set<String> treatAsDeclarations) {

		if ( isClassOrSuperclassTable( subclassTableNumber ) ) {
			final boolean shouldInnerJoin = canInnerJoin
					&& !isInverseTable( subclassTableNumber )
					&& !isNullableTable( subclassTableNumber );
			// the table is either this persister's driving table or (one of) its super class persister's driving
			// tables which can be inner joined as long as the `shouldInnerJoin` condition resolves to true
			return shouldInnerJoin ? JoinType.INNER_JOIN : JoinType.LEFT_OUTER_JOIN;
		}

		// otherwise we have a subclass table and need to look a little deeper...

		// IMPL NOTE : By default includeSubclasses indicates that all subclasses should be joined and that each
		// subclass ought to be joined by outer-join.  However, TREAT-AS always requires that an inner-join be used
		// so we give TREAT-AS higher precedence...

		if ( isSubclassTableIndicatedByTreatAsDeclarations( subclassTableNumber, treatAsDeclarations ) ) {
			return JoinType.INNER_JOIN;
		}

		if ( includeSubclasses
				&& !isSubclassTableSequentialSelect( subclassTableNumber )
				&& !isSubclassTableLazy( subclassTableNumber ) ) {
			return JoinType.LEFT_OUTER_JOIN;
		}

		return JoinType.NONE;
	}
