	@Test
	public void createBinderWithGlobalInitialization() throws Exception {
		ConversionService conversionService = new DefaultFormattingConversionService();
		bindingInitializer.setConversionService(conversionService);

		WebDataBinderFactory factory = createFactory("initBinder", WebDataBinder.class);
		WebDataBinder dataBinder = factory.createBinder(this.webRequest, null, null);

		assertSame(conversionService, dataBinder.getConversionService());
	}
