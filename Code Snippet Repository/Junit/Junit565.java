	@Override
	public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
			throws ParameterResolutionException {
		Object argument = arguments[parameterContext.getIndex()];
		Parameter parameter = parameterContext.getParameter();
		Optional<ConvertWith> annotation = AnnotationUtils.findAnnotation(parameter, ConvertWith.class);
		// @formatter:off
		ArgumentConverter argumentConverter = annotation.map(ConvertWith::value)
				.map(clazz -> (ArgumentConverter) ReflectionUtils.newInstance(clazz))
				.map(converter -> AnnotationConsumerInitializer.initialize(parameter, converter))
				.orElse(DefaultArgumentConverter.INSTANCE);
		// @formatter:on
		try {
			return argumentConverter.convert(argument, parameterContext);
		}
		catch (Exception ex) {
			throw new ParameterResolutionException("Error resolving parameter at index " + parameterContext.getIndex(),
				ex);
		}
	}
