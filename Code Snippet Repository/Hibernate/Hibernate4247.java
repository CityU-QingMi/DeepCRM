	@SuppressWarnings("")
	private <P> ParameterRegistrationImplementor<P> locateParameterRegistration(String name) {
		assert name != null;

		if ( parameterStrategy == ParameterStrategy.POSITIONAL ) {
			throw new IllegalArgumentException( "Expecting positional parameter" );
		}

		for ( ParameterRegistrationImplementor<?> registeredParameter : registeredParameters ) {
			if ( name.equals( registeredParameter.getName() ) ) {
				return (ParameterRegistrationImplementor<P>) registeredParameter;
			}
		}

		throw new IllegalArgumentException( "Unknown parameter registration name [" + name + "]" );
	}
