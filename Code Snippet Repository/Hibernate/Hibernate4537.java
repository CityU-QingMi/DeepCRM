	@SuppressWarnings("")
	public <T> QueryParameterBinding<T> locateBinding(QueryParameter<T> parameter) {
		// see if this exact instance is known as a key
		if ( parameterBindingMap.containsKey( parameter ) ) {
			return parameterBindingMap.get( parameter );
		}

		// if the incoming parameter has a name, try to find it by name
		if ( StringHelper.isNotEmpty( parameter.getName() ) ) {
			final QueryParameterBinding binding = locateBinding( parameter.getName() );
			if ( binding != null ) {
				return binding;
			}
		}

		// if the incoming parameter has a position, try to find it by position
		if ( parameter.getPosition() != null ) {
			final QueryParameterBinding binding = locateBinding( parameter.getPosition() );
			if ( binding != null ) {
				return binding;
			}
		}

		return null;
	}
