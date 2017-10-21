	public static String createCollectionSubquery(
			JoinSequence joinSequence,
			Map enabledFilters,
			String[] columns) {
		try {
			JoinFragment join = joinSequence.toJoinFragment( enabledFilters, true );
			return "select " + StringHelper.join( ", ", columns )
					+ " from " + join.toFromFragmentString().substring( 2 )
					+ " where " + join.toWhereFragmentString().substring( 5 );
		}
		catch (MappingException me) {
			throw new QueryException( me );
		}
	}
