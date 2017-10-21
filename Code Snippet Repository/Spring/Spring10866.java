	@Test
	@SuppressWarnings("")
	public void missingOptionalParamArray() throws Exception {
		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		WebDataBinderFactory binderFactory = new DefaultDataBinderFactory(initializer);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(Optional.class, Integer[].class);
		Object result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.empty(), result);

		result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.class, result.getClass());
		assertFalse(((Optional) result).isPresent());
	}
