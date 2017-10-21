	@Override
	public String getEntityName(Criteria subcriteria, String propertyName) {
		if ( propertyName.indexOf( '.' ) > 0 ) {
			final String root = StringHelper.root( propertyName );
			final Criteria crit = getAliasedCriteria( root );
			if ( crit != null ) {
				return getEntityName( crit );
			}
		}
		return getEntityName( subcriteria );
	}
