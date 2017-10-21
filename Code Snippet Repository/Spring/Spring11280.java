	private String getModelAttributeName(MethodParameter parameter) {
		Assert.isTrue(parameter.getParameterIndex() > 0,
				"Errors argument must be immediately after a model attribute argument");

		int index = parameter.getParameterIndex() - 1;
		MethodParameter attributeParam = MethodParameter.forExecutable(parameter.getExecutable(), index);
		ReactiveAdapter adapter = getAdapterRegistry().getAdapter(attributeParam.getParameterType());

		Assert.isNull(adapter, "Errors/BindingResult cannot be used with an async model attribute. " +
				"Either declare the model attribute without the async wrapper type " +
				"or handle WebExchangeBindException through the async type.");

		ModelAttribute ann = parameter.getParameterAnnotation(ModelAttribute.class);
		if (ann != null && StringUtils.hasText(ann.value())) {
			return ann.value();
		}
		return Conventions.getVariableNameForParameter(attributeParam);
	}
