	@Test
	@SuppressWarnings("")
	public void resolveOptionalParamValue() throws Exception {
		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		WebDataBinderFactory binderFactory = new DefaultDataBinderFactory(initializer);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(Optional.class, Integer.class);
		Object result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.empty(), result);

		this.request.addParameter("name", "123");
		result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.class, result.getClass());
		assertEquals(123, ((Optional) result).get());
	}
