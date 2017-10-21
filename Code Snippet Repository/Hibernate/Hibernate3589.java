	@Override
	public String getSQLAlias(Criteria criteria, String propertyName) {
		if ( propertyName.indexOf( '.' ) > 0 ) {
			final String root = StringHelper.root( propertyName );
			final Criteria subcriteria = getAliasedCriteria( root );
			if ( subcriteria != null ) {
				return getSQLAlias( subcriteria );
			}
		}
		return getSQLAlias( criteria );
	}
