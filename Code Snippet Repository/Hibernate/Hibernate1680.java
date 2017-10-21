	@Override
	public String[] getColumnAliases(String alias, final int loc) {
		int position = loc;
		for ( Projection projection : elements ) {
			final String[] aliases = projection.getColumnAliases( alias, position );
			if ( aliases != null ) {
				return aliases;
			}
			position += projection.getColumnAliases( position ).length;
		}
		return null;
	}
