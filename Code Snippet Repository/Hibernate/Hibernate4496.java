	@SuppressWarnings("")
	private <P> QueryParameterBinding<P> locateBinding(Parameter<P> parameter) {
		if ( parameter instanceof QueryParameter ) {
			return queryParameterBindings.getBinding( (QueryParameter) parameter );
		}
		else if ( parameter.getName() != null ) {
			return queryParameterBindings.getBinding( parameter.getName() );
		}
		else if ( parameter.getPosition() != null ) {
			return queryParameterBindings.getBinding( parameter.getPosition() );
		}

		throw getExceptionConverter().convert(
				new IllegalArgumentException( "Could not resolve binding for given parameter reference [" + parameter + "]" )
		);
	}
