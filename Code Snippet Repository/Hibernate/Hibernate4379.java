	@Override
	public <T> QueryImplementor<X> setParameter(QueryParameter<T> parameter, T val) {
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), val );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), val );
		}
		return this;
	}
