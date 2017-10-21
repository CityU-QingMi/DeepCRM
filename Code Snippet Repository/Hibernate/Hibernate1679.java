	@Override
	public String[] getColumnAliases(final int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		int position = loc;
		final List<String> result = new ArrayList<String>( getLength() );
		for ( Projection projection : elements ) {
			final String[] aliases = getColumnAliases( position, criteria, criteriaQuery, projection );
			Collections.addAll( result, aliases );
			position += aliases.length;
		}
		return result.toArray( new String[result.size()] );
	}
