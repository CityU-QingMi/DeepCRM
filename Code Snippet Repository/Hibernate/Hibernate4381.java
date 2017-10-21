	@Override
	public <P> QueryImplementor<X> setParameterList(QueryParameter<P> parameter, Collection<P> values) {
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( parameter );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), values );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), values );
		}
		return this;
	}
