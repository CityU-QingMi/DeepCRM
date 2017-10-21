	@SuppressWarnings("")
	private <P> ParameterRegistrationImplementor<P> locateParameterRegistration(int position) {
		if ( parameterStrategy == ParameterStrategy.NAMED ) {
			throw new IllegalArgumentException( "Expecting named parameter" );
		}

		for ( ParameterRegistrationImplementor<?> registeredParameter : registeredParameters ) {
			if ( registeredParameter.getPosition() != null && registeredParameter.getPosition() == position ) {
				return (ParameterRegistrationImplementor<P>) registeredParameter;
			}
		}

		throw new IllegalArgumentException( "Unknown parameter registration position [" + position + "]" );
	}
