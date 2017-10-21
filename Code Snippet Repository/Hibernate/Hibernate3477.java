	protected static int countEntityPersisters(List associations)
			throws MappingException {
		int result = 0;
		for ( Object association : associations ) {
			final OuterJoinableAssociation oj = (OuterJoinableAssociation) association;
			if ( oj.getJoinable().consumesEntityAlias() ) {
				result++;
			}
		}
		return result;
	}
