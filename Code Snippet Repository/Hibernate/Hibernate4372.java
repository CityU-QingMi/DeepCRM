	@Override
	public QueryImplementor<X> setParameter(Parameter<ZonedDateTime> param, ZonedDateTime value, TemporalType temporalType){
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), value, temporalType );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), value, temporalType );
		}
		return this;
	}
