	@Test
	public void optionalMultipartFileWithoutMultipartRequest() throws Exception {
		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		WebDataBinderFactory binderFactory = new DefaultDataBinderFactory(initializer);

		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(Optional.class, MultipartFile.class);
		Object actual = resolver.resolveArgument(param, null, webRequest, binderFactory);

		assertEquals(Optional.empty(), actual);
	}
