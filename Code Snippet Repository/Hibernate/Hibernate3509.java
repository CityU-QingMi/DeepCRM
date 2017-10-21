	protected int bindNamedParameters(
			final PreparedStatement statement,
			final Map<String, TypedValue> namedParams,
			final int startIndex,
			final SharedSessionContractImplementor session) throws SQLException, HibernateException {
		int result = 0;
		if ( CollectionHelper.isEmpty( namedParams ) ) {
			return result;
		}

		for ( String name : namedParams.keySet() ) {
			TypedValue typedValue = namedParams.get( name );
			int columnSpan = typedValue.getType().getColumnSpan( getFactory() );
			int[] locs = getNamedParameterLocs( name );
			for ( int loc : locs ) {
				if ( DEBUG_ENABLED ) {
					LOG.debugf(
							"bindNamedParameters() %s -> %s [%s]",
							typedValue.getValue(),
							name,
							loc + startIndex
					);
				}
				int start = loc * columnSpan + startIndex;
				typedValue.getType().nullSafeSet( statement, typedValue.getValue(), start, session );
			}
			result += locs.length;
		}
		return result;
	}
