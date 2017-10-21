	protected JoinFragment createJoin(
			String name,
			boolean innerJoin,
			boolean includeSubclasses,
			Set<String> treatAsDeclarations) {
		// IMPL NOTE : all joins join to the pk of the driving table
		final String[] idCols = StringHelper.qualify( name, getIdentifierColumnNames() );
		final JoinFragment join = getFactory().getDialect().createOuterJoinFragment();
		final int tableSpan = getSubclassTableSpan();
		// IMPL NOTE : notice that we skip the first table; it is the driving table!
		for ( int j = 1; j < tableSpan; j++ ) {
			final JoinType joinType = determineSubclassTableJoinType(
					j,
					innerJoin,
					includeSubclasses,
					treatAsDeclarations
			);

			if ( joinType != null && joinType != JoinType.NONE ) {
				join.addJoin(
						getSubclassTableName( j ),
						generateTableAlias( name, j ),
						idCols,
						getSubclassTableKeyColumns( j ),
						joinType
				);
			}
		}
		return join;
	}
