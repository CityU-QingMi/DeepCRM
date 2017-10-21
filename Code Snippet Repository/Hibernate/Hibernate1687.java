	@Override
	public String[] getColumnAliases(int loc, Criteria criteria, CriteriaQuery criteriaQuery) {
		final int numColumns =  getColumnCount( criteria, criteriaQuery );
		final String[] aliases = new String[ numColumns ];
		for (int i = 0; i < numColumns; i++) {
			aliases[i] = getAliasForLocation( loc );
			loc++;
		}
		return aliases;
	}
