	@Deprecated
	@SuppressWarnings("")
	private <T> QueryParameterBinding<T> getAndRemoveBinding(QueryParameter<T> parameter) {
		// see if this exact instance is known as a key
		if ( parameterBindingMap.containsKey( parameter ) ) {
			return parameterBindingMap.remove( parameter );
		}

		// if the incoming parameter has a name, try to find it by name
		if ( StringHelper.isNotEmpty( parameter.getName() ) ) {
			final QueryParameterBinding binding = locateAndRemoveBinding( parameter.getName() );
			if ( binding != null ) {
				return binding;
			}
		}

		// NOTE : getAndRemoveBinding is only intended for usage from #transformQueryParameterBindingToQueryParameterListBinding
		//		which only supports named parameters, so there is no need to look into legacy positional parameters

		throw new IllegalArgumentException(
				"Could not resolve QueryParameter reference [" + parameter + "] to QueryParameterBinding"
		);
	}
