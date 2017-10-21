	public void validateTypes(SelectClause selectClause) throws QueryException {
		Type[] selectTypes = selectClause.getQueryReturnTypes();
		if ( selectTypes.length + selectClause.getTotalParameterCount() != types.length ) {
			throw new QueryException( "number of select types did not match those for insert" );
		}

		int parameterCount = 0;
		for ( int i = 0; i < types.length; i++ ) {
			if ( selectClause.getParameterPositions().contains( i ) ) {
				parameterCount++;
			}
			else if ( !areCompatible( types[i], selectTypes[i - parameterCount] ) ) {
				throw new QueryException(
						"insertion type [" + types[i] + "] and selection type [" +
								selectTypes[i - parameterCount] + "] at position " + i + " are not compatible"
				);
			}
		}

		// otherwise, everything ok.
	}
