	private void registerParameter(ParameterRegistrationImplementor parameter) {
		if ( StringHelper.isNotEmpty( parameter.getName() ) ) {
			prepareForNamedParameters();
		}
		else if ( parameter.getPosition() != null ) {
			prepareForPositionalParameters();
		}
		else {
			throw new IllegalArgumentException( "Given parameter did not define name or position [" + parameter + "]" );
		}
		((ProcedureParameterMetadata)getParameterMetadata()).registerParameter( new ProcedureParameterImpl( parameter ) );

		registeredParameters.add( parameter );
	}
