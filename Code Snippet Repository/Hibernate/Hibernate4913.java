	@Override
	public List transformList(List list) {
		List<Object> result = new ArrayList<Object>( list.size() );
		Set<Identity> distinct = new HashSet<Identity>();
		for ( Object entity : list ) {
			if ( distinct.add( new Identity( entity ) ) ) {
				result.add( entity );
			}
		}
		LOG.debugf( "Transformed: %s rows to: %s distinct results", list.size(), result.size() );
		return result;
	}
