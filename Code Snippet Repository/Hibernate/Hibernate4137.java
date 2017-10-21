	protected JoinFragment createJoin(int[] tableNumbers, String drivingAlias) {
		final String[] keyCols = StringHelper.qualify( drivingAlias, getSubclassTableKeyColumns( tableNumbers[0] ) );
		final JoinFragment jf = getFactory().getDialect().createOuterJoinFragment();
		// IMPL NOTE : notice that we skip the first table; it is the driving table!
		for ( int i = 1; i < tableNumbers.length; i++ ) {
			final int j = tableNumbers[i];
			jf.addJoin(
					getSubclassTableName( j ),
					generateTableAlias( getRootAlias(), j ),
					keyCols,
					getSubclassTableKeyColumns( j ),
					isInverseSubclassTable( j ) || isNullableSubclassTable( j )
							? JoinType.LEFT_OUTER_JOIN
							: JoinType.INNER_JOIN
			);
		}
		return jf;
	}
