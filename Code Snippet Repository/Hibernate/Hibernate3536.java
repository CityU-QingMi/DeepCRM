	private static int getPosition(String lhsAlias, List associations) {
		int result = 0;
		for ( Object association : associations ) {
			final OuterJoinableAssociation oj = (OuterJoinableAssociation) association;
			if ( oj.getJoinable().consumesEntityAlias() /*|| oj.getJoinable().consumesCollectionAlias() */ ) {
				if ( oj.rhsAlias.equals( lhsAlias ) ) {
					return result;
				}
				result++;
			}
		}
		return -1;
	}
