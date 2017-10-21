	public FromClause locateChildFromClauseWithJoinByPath(String path) {
		if ( childFromClauses != null && !childFromClauses.isEmpty() ) {
			for ( FromClause child : childFromClauses ) {
				if ( child.findJoinByPathLocal( path ) != null ) {
					return child;
				}
			}
		}
		return null;
	}
