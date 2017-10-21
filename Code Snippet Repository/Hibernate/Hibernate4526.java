	public static QueryParameterBindingsImpl from(
			ParameterMetadata parameterMetadata,
			SessionFactoryImplementor sessionFactory,
			boolean queryParametersValidationEnabled) {
		if ( parameterMetadata == null ) {
			return new QueryParameterBindingsImpl(
					sessionFactory,
					parameterMetadata,
					queryParametersValidationEnabled
			);
		}
		else {
			return new QueryParameterBindingsImpl(
					sessionFactory,
					parameterMetadata.collectAllParameters(),
					parameterMetadata, queryParametersValidationEnabled
			);
		}
	}
