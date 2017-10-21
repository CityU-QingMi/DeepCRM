	public int[] getNamedParameterLocations(String name) throws QueryException {
		Object o = namedParameters.get( name );
		if ( o == null ) {
			throw new QueryException(
					QueryTranslator.ERROR_NAMED_PARAMETER_DOES_NOT_APPEAR + name,
					queryTranslatorImpl.getQueryString()
			);
		}
		if ( o instanceof Integer ) {
			return new int[] {(Integer) o};
		}
		else {
			return ArrayHelper.toIntArray( (ArrayList) o );
		}
	}
