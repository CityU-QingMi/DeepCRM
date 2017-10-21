	public void verifyParametersBound(boolean reserveFirstParameter) {
		// verify named parameters bound
		for ( Map.Entry<QueryParameter, QueryParameterBinding> bindEntry : parameterBindingMap.entrySet() ) {
			if ( !bindEntry.getValue().isBound() ) {
				if ( bindEntry.getKey().getName() != null ) {
					throw new QueryException( "Named parameter [" + bindEntry.getKey().getName() + "] not set" );
				}
				else {
					throw new QueryException( "Parameter memento [" + bindEntry.getKey() + "] not set" );
				}
			}
		}
		// verify position parameters bound
		int startIndex = 0;
		if ( !parameterMetadata.isOrdinalParametersZeroBased() ) {
			startIndex = 1;
		}
		for ( int i = startIndex; i < positionalParameterBindings.size(); i++ ) {
			QueryParameterBinding binding = null;
			if ( parameterMetadata.isOrdinalParametersZeroBased() ) {
				binding = positionalParameterBindings.get( i );
			}
			else {
				binding = positionalParameterBindings.get( i - 1 );
			}
			if ( binding == null || !binding.isBound() ) {
				throw new QueryException( "Positional parameter [" + i + "] not set" );
			}
		}
		// verify position parameter count is correct
		final int positionalValueSpan = calculatePositionalValueSpan( reserveFirstParameter );
		final int positionCounts = parameterMetadata.getPositionalParameterCount();
		if ( positionCounts != positionalValueSpan ) {
			if ( reserveFirstParameter && positionCounts - 1 != positionalValueSpan ) {
				throw new QueryException(
						"Expected positional parameter count: " +
								( positionCounts - 1 ) +
								", actually detected " + positionalValueSpan
				);
			}
			else if ( !reserveFirstParameter ) {
				throw new QueryException(
						"Expected positional parameter count: " +
								( positionCounts ) +
								", actually detected " + positionalValueSpan
				);
			}
		}
	}
