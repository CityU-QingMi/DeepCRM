	@Override
	@SuppressWarnings({ "" })
	public <T> QueryImplementor<X> setParameter(Parameter<T> param, T t) {
		entityManager.checkOpen( false );
		final ExplicitParameterInfo parameterInfo = resolveParameterInfo( param );
		if ( parameterInfo.isNamed() ) {
			jpqlQuery.setParameter( parameterInfo.getName(), t );
		}
		else {
			jpqlQuery.setParameter( parameterInfo.getPosition(), t );
		}
		return this;
	}
