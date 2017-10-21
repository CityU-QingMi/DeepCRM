	@Override
	public <P> QueryImplementor<X> setParameter(
			QueryParameter<P> parameter, P val, TemporalType temporalType) {
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), val, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), val, temporalType );
		}
		return this;
	}
