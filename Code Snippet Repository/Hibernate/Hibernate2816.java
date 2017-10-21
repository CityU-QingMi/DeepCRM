	@Override
	public int[] getNamedParameterLocs(String name) throws QueryException {
		Object o = namedParameters.get( name );
		if ( o == null ) {
			throw new QueryException( ERROR_NAMED_PARAMETER_DOES_NOT_APPEAR + name, queryString );
		}
		if ( o instanceof Integer ) {
			return new int[] {(Integer) o};
		}
		else {
			return ArrayHelper.toIntArray( (ArrayList) o );
		}
	}
