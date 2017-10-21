	private List<ParameterSpecification> getParameterSpecification() {
		List<ParameterSpecification> parameterSpecifications =
			embeddedParameters.stream()
					.filter( o -> o instanceof  DynamicFilterParameterSpecification )
					.collect( Collectors.toList() );

		parameterSpecifications.addAll(
			embeddedParameters.stream()
					.filter( o -> ! (o instanceof  DynamicFilterParameterSpecification ) )
					.collect( Collectors.toList() ) );
		return parameterSpecifications;
	}
