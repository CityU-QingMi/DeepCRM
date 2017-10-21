	@Override
	@SuppressWarnings("")
	public QueryImplementor setParameter(String name, Object value) {
		if ( value instanceof TypedParameterValue ) {
			final TypedParameterValue  typedValueWrapper = (TypedParameterValue) value;
			setParameter( name, typedValueWrapper.getValue(), typedValueWrapper.getType() );
		}
		else if ( value instanceof Collection && !isRegisteredAsBasicType( value.getClass() ) ) {
			setParameterList( name, (Collection) value );
		}
		else {
			queryParameterBindings.getBinding( name ).setBindValue( value );
		}

		return this;
	}
