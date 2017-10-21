	protected static int countCollectionPersisters(List associations)
			throws MappingException {
		int result = 0;
		for ( Object association : associations ) {
			final OuterJoinableAssociation oj = (OuterJoinableAssociation) association;
			if ( oj.getJoinType() == JoinType.LEFT_OUTER_JOIN &&
					oj.getJoinable().isCollection() &&
					!oj.hasRestriction() ) {
				result++;
			}
		}
		return result;
	}
