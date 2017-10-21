	@Override
	@SuppressWarnings("")
	public <P> QueryImplementor setParameter(Parameter<P> parameter, P value) {
		if ( value instanceof TypedParameterValue ) {
			setParameter( parameter, ( (TypedParameterValue) value ).getValue(), ( (TypedParameterValue) value ).getType() );
		}
		else if ( value instanceof Collection && !isRegisteredAsBasicType( value.getClass() ) ) {
			locateListBinding( parameter ).setBindValues( (Collection) value );
		}
		else {
			locateBinding( parameter ).setBindValue( value );
		}

		return this;
	}
