	protected int bindNamedParameters(
			final PreparedStatement statement,
			final Map namedParams,
			final int startIndex,
			final SharedSessionContractImplementor session) throws SQLException, HibernateException {
		if ( namedParams != null ) {
			// assumes that types are all of span 1
			final Iterator itr = namedParams.entrySet().iterator();
			final boolean debugEnabled = log.isDebugEnabled();
			int result = 0;
			while ( itr.hasNext() ) {
				final Map.Entry e = (Map.Entry) itr.next();
				final String name = (String) e.getKey();
				final TypedValue typedval = (TypedValue) e.getValue();
				final int[] locs = getNamedParameterLocs( name );
				for ( int loc : locs ) {
					if ( debugEnabled ) {
						log.debugf(
								"bindNamedParameters() %s -> %s [%s]",
								typedval.getValue(),
								name,
								loc + startIndex
						);
					}
					typedval.getType().nullSafeSet( statement, typedval.getValue(), loc + startIndex, session );
				}
				result += locs.length;
			}
			return result;
		}
		else {
			return 0;
		}
	}
