	private static int bindNamedParameters(
			final PreparedStatement ps,
			final Map namedParams,
			final int start,
			final NamedParameterSource source,
			final SessionImplementor session) throws SQLException, HibernateException {
		if ( namedParams != null ) {
			final boolean debugEnabled = LOG.isDebugEnabled();
			// assumes that types are all of span 1
			final Iterator iter = namedParams.entrySet().iterator();
			int result = 0;
			while ( iter.hasNext() ) {
				final Map.Entry e = (Map.Entry) iter.next();
				final String name = (String) e.getKey();
				final TypedValue typedVal = (TypedValue) e.getValue();
				final int[] locations = source.getNamedParameterLocations( name );
				for ( int location : locations ) {
					if ( debugEnabled ) {
						LOG.debugf(
								"bindNamedParameters() %s -> %s [%s]",
								typedVal.getValue(),
								name,
								location + start
						);
					}
					typedVal.getType().nullSafeSet( ps, typedVal.getValue(), location + start, session );
				}
				result += locations.length;
			}
			return result;
		}
		return 0;
	}
