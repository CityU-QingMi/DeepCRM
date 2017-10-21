	public AbstractProducedQuery(
			SharedSessionContractImplementor producer,
			ParameterMetadata parameterMetadata) {
		this.producer = producer;
		this.parameterMetadata = parameterMetadata;
		this.queryParameterBindings = QueryParameterBindingsImpl.from(
				parameterMetadata,
				producer.getFactory(),
				producer.isQueryParametersValidationEnabled()
		);
	}
