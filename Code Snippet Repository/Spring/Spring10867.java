	@Test
	@SuppressWarnings("")
	public void resolveOptionalParamList() throws Exception {
		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		WebDataBinderFactory binderFactory = new DefaultDataBinderFactory(initializer);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(Optional.class, List.class);
		Object result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.empty(), result);

		this.request.addParameter("name", "123", "456");
		result = resolver.resolveArgument(param, null, webRequest, binderFactory);
		assertEquals(Optional.class, result.getClass());
		assertEquals(Arrays.asList("123", "456"), ((Optional) result).get());
	}
