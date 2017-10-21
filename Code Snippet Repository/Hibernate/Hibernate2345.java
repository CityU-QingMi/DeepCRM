	public void validateParameters() throws QueryException {
		final int types = positionalParameterTypes == null ? 0 : positionalParameterTypes.length;
		final int values = positionalParameterValues == null ? 0 : positionalParameterValues.length;
		if ( types != values ) {
			throw new QueryException(
					"Number of positional parameter types:" + types +
							" does not match number of positional parameters: " + values
			);
		}
	}
