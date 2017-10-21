	@SuppressWarnings("")
	public <T> QueryParameterBinding<T> getBinding(QueryParameter<T> parameter) {
		final QueryParameterBinding<T> binding = locateBinding( parameter );

		if ( binding == null ) {
			throw new IllegalArgumentException(
					"Could not resolve QueryParameter reference [" + parameter + "] to QueryParameterBinding"
			);
		}

		return binding;
	}
