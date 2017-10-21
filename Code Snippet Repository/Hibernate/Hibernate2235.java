	private int bindNamedParameters(
			final PreparedStatement ps,
			final Map namedParams,
			final int start,
			final SharedSessionContractImplementor session) throws SQLException {
		if ( namedParams != null ) {
			// assumes that types are all of span 1
			final Iterator iter = namedParams.entrySet().iterator();
			int result = 0;
			while ( iter.hasNext() ) {
				final Map.Entry e = (Map.Entry) iter.next();
				final String name = (String) e.getKey();
				final TypedValue typedval = (TypedValue) e.getValue();
				final int[] locs = getNamedParameterLocs( name );
				for ( int loc : locs ) {
					LOG.debugf( "bindNamedParameters() %s -> %s [%s]", typedval.getValue(), name, loc + start );
					typedval.getType().nullSafeSet(
							ps,
							typedval.getValue(),
							loc + start,
							session
					);
				}
				result += locs.length;
			}
			return result;
		}

		return 0;
	}
