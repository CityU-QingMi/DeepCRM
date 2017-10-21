	@Override
	public String getPropertyName(String propertyName) {
		if ( propertyName.indexOf( '.' ) > 0 ) {
			final String root = StringHelper.root( propertyName );
			final Criteria criteria = getAliasedCriteria( root );
			if ( criteria != null ) {
				return propertyName.substring( root.length() + 1 );
			}
		}
		return propertyName;
	}
