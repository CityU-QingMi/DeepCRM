	public boolean isAlias(String alias) {
		FromClause from = walker.getCurrentFromClause();
		while ( from.isSubQuery() ) {
			if ( from.containsClassAlias( alias ) ) {
				return true;
			}
			from = from.getParentFromClause();
		}
		return from.containsClassAlias( alias );
	}
