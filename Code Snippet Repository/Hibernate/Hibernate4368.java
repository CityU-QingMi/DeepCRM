	@Override
	@SuppressWarnings({ "" })
	public <T> T getParameterValue(Parameter<T> param) {
		entityManager.checkOpen( false );
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			return ( T ) jpqlQuery.getParameterValue( parameterInfo.getName() );
		}
		else {
			return ( T ) jpqlQuery.getParameterValue( parameterInfo.getPosition() );
		}
	}
