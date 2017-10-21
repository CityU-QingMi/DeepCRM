	private boolean isSubclassAliasDereferenced(Join join, String withClauseFragment) {
		if ( join.getJoinable() instanceof AbstractEntityPersister ) {
			AbstractEntityPersister persister = (AbstractEntityPersister) join.getJoinable();
			int subclassTableSpan = persister.getSubclassTableSpan();
			for ( int j = 1; j < subclassTableSpan; j++ ) {
				String subclassAlias = AbstractEntityPersister.generateTableAlias( join.getAlias(), j );
				if ( isAliasDereferenced( withClauseFragment, subclassAlias ) ) {
					return true;
				}
			}
		}
		return false;
	}
