	@Override
	public int[] getNamedParameterLocs(String name) throws QueryException {
		Object loc = namedParameterBindPoints.get( name );
		if ( loc == null ) {
			throw new QueryException(
					"Named parameter does not appear in Query: " + name,
					sql
			);
		}
		if ( loc instanceof Integer ) {
			return new int[] {(Integer) loc};
		}
		else {
			return ArrayHelper.toIntArray( (List) loc );
		}
	}
