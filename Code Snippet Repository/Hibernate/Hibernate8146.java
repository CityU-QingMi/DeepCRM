	private Map getTokens(String sql) {
		Map<String,Integer> result = new TreeMap<String,Integer>();
		if ( sql == null ) {
			return result;
		}
		result.put( "=", Integer.valueOf( StringHelper.countUnquoted( sql, '=' ) ) );
		StringTokenizer tokenizer = new StringTokenizer( sql, "(),= " );
		while ( tokenizer.hasMoreTokens() ) {
			String fragment = tokenizer.nextToken();
/**/
/**/
			Integer count = result.get(fragment);
			if ( count == null ) {
				count = Integer.valueOf(1);
			}
			else {
				count = Integer.valueOf( count.intValue() + 1 );
			}
			result.put(fragment, count);
		}
		return result;
	}
