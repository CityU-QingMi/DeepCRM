	private QueryParameterBindingsImpl(
			SessionFactoryImplementor sessionFactory,
			Set<QueryParameter<?>> queryParameters,
			ParameterMetadata parameterMetadata,
			boolean queryParametersValidationEnabled) {
		this.sessionFactory = sessionFactory;
		this.parameterMetadata = parameterMetadata;
		this.queryParametersValidationEnabled = queryParametersValidationEnabled;
		this.positionalParameterBindings = new TreeMap<>(  );

		if ( queryParameters == null || queryParameters.isEmpty() ) {
			parameterBindingMap = Collections.emptyMap();
		}
		else {
			parameterBindingMap = new HashMap<>();

			for ( QueryParameter queryParameter : queryParameters ) {
				if ( queryParameter.getPosition() == null ) {
					// only cache the non-positional parameters in this map
					// positional parameters will be bound dynamically with getBinding(int)
					parameterBindingMap.put( queryParameter, makeBinding( queryParameter ) );
				}
			}
		}

		parameterListBindingMap = new HashMap<>();
	}
