	private int[] getNamedParameterLocs(String name) throws QueryException {
		final Object loc = customQuery.getNamedParameterBindPoints().get( name );
		if ( loc == null ) {
			throw new QueryException(
					"Named parameter does not appear in Query: " + name,
					customQuery.getSQL() );
		}
		if ( loc instanceof Integer ) {
			return new int[] { (Integer) loc };
		}
		else {
			return ArrayHelper.toIntArray( (List) loc );
		}
	}
