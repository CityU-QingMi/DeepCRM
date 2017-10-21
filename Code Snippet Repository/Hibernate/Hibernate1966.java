	private boolean needsTableGroupJoin(List<Join> joins, String withClauseFragment) {
		// If the rewrite is disabled or we don't have a with clause, we don't need a table group join
		if ( !collectionJoinSubquery || StringHelper.isEmpty( withClauseFragment ) ) {
			return false;
		}
		// If we only have one join, a table group join is only necessary if subclass columns are used in the with clause
		if ( joins.size() < 2 ) {
			return isSubclassAliasDereferenced( joins.get( 0 ), withClauseFragment );
		}
		// If more than one table is involved and this is not an inner join, we definitely need a table group join
		// i.e. a left join has to be made for the table group to retain the join semantics
		if ( joins.get( 0 ).getJoinType() != JoinType.INNER_JOIN ) {
			return true;
		}
		// If a subclass columns is used, we need a table group, otherwise we generate wrong SQL by putting the ON condition to the first join
		if ( isSubclassAliasDereferenced( joins.get( 0 ), withClauseFragment ) ) {
			return true;
		}

		// Normally, the ON condition of a HQL join is put on the ON clause of the first SQL join
		// Since the ON condition could refer to columns from subsequently joined tables i.e. joins with index > 0
		// or could refer to columns of subclass tables, the SQL could be wrong
		// To avoid generating wrong SQL, we detect these cases here i.e. a subsequent join alias is used in the ON condition
		// If we find out that this is the case, we return true and generate a table group join

		// Skip the first since that is the driving join
		for ( int i = 1; i < joins.size(); i++ ) {
			Join join = joins.get( i );

			if ( isAliasDereferenced( withClauseFragment, join.getAlias() ) || isSubclassAliasDereferenced( join, withClauseFragment ) ) {
				return true;
			}
		}

		return false;
	}
