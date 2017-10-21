	@Override
	@SuppressWarnings({ "" })
	public QueryImplementor<X> setParameter(Parameter<Calendar> param, Calendar calendar, TemporalType temporalType) {
		entityManager.checkOpen( false );
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), calendar, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), calendar, temporalType );
		}
		return this;
	}
