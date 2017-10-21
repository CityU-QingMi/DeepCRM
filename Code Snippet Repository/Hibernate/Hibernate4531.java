	@Deprecated
	private <T> QueryParameterListBinding<T> transformQueryParameterBindingToQueryParameterListBinding(QueryParameter<T> queryParameter) {
		log.debugf( "Converting QueryParameterBinding to QueryParameterListBinding for given QueryParameter : %s", queryParameter );
		final QueryParameterBinding binding = getAndRemoveBinding( queryParameter );
		if ( binding == null ) {
			throw new IllegalArgumentException(
					"Could not locate QueryParameterBinding for given QueryParameter : " + queryParameter +
							"; parameter list must be defined using named parameter"
			);
		}

		final QueryParameterListBinding<T> convertedBinding = new QueryParameterListBindingImpl<>(
				binding.getBindType(),
				shouldValidateBindingValue()
		);
		parameterListBindingMap.put( queryParameter, convertedBinding );

		return convertedBinding;
	}
