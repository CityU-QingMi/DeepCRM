	@Override
	public <P> QueryImplementor<X> setParameter(QueryParameter<P> parameter, P value, Type type) {
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, type );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, type );
		}
		return this;
	}
