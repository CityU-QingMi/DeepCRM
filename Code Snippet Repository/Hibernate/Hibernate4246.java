	@SuppressWarnings("")
	private  <P> ParameterRegistrationImplementor<P> locateParameterRegistration(Parameter<P> parameter) {
		if ( parameter.getName() != null ) {
			return locateParameterRegistration( parameter.getName() );
		}

		if ( parameter.getPosition() != null ) {
			return locateParameterRegistration( parameter.getPosition() );
		}

		throw getExceptionConverter().convert(
				new IllegalArgumentException( "Could not resolve registration for given parameter reference [" + parameter + "]" )
		);
	}
