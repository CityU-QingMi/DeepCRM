	@Override
	public String[] getColumnAliases(String alias, int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		int position = loc;
		for ( Projection projection : elements ) {
			final String[] aliases = getColumnAliases( alias, position, criteria, criteriaQuery, projection );
			if ( aliases != null ) {
				return aliases;
			}
			position += getColumnAliases( position, criteria, criteriaQuery, projection ).length;
		}
		return null;
	}
