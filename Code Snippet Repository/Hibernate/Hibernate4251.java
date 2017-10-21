	@Override
	public ParameterRegistrationImplementor getParameterRegistration(int position) {
		if ( parameterStrategy != ParameterStrategy.POSITIONAL ) {
			throw new ParameterStrategyException(
					"Attempt to access positional parameter [" + position + "] but ProcedureCall using named parameters"
			);
		}
		for ( ParameterRegistrationImplementor parameter : registeredParameters ) {
			if ( position == parameter.getPosition() ) {
				return parameter;
			}
		}
		throw new NoSuchParameterException( "Could not locate parameter registered using that position [" + position + "]" );
	}
