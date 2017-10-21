	@Override
	public ParameterRegistrationImplementor getParameterRegistration(String name) {
		if ( parameterStrategy != ParameterStrategy.NAMED ) {
			throw new ParameterStrategyException( "Names were not used to register parameters with this stored procedure call" );
		}
		for ( ParameterRegistrationImplementor parameter : registeredParameters ) {
			if ( name.equals( parameter.getName() ) ) {
				return parameter;
			}
		}
		throw new NoSuchParameterException( "Could not locate parameter registered under that name [" + name + "]" );
	}
