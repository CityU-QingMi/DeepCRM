	@Override
	@SuppressWarnings({ "" })
	public QueryImplementor<X> setParameter(Parameter<Date> param, Date date, TemporalType temporalType) {
		entityManager.checkOpen( false );
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), date, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), date, temporalType );
		}
		return this;
	}
