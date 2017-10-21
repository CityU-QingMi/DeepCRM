	@Override
	public void contributeMethodArgument(MethodParameter parameter, Object value,
			UriComponentsBuilder builder, Map<String, Object> uriVariables, ConversionService conversionService) {

		for (Object contributor : this.contributors) {
			if (contributor instanceof UriComponentsContributor) {
				UriComponentsContributor ucc = (UriComponentsContributor) contributor;
				if (ucc.supportsParameter(parameter)) {
					ucc.contributeMethodArgument(parameter, value, builder, uriVariables, conversionService);
					break;
				}
			}
			else if (contributor instanceof HandlerMethodArgumentResolver) {
				if (((HandlerMethodArgumentResolver) contributor).supportsParameter(parameter)) {
					break;
				}
			}
		}
	}
