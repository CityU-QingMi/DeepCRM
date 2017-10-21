	@Override
	public String[] getColumnAliases(final int loc) {
		int position = loc;
		final List<String> result = new ArrayList<String>( getLength() );
		for ( Projection projection : elements ) {
			final String[] aliases = projection.getColumnAliases( position );
			Collections.addAll( result, aliases );
			position += aliases.length;
		}
		return result.toArray( new String[ result.size() ] );
	}
