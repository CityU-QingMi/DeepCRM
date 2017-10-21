	@Test
	public void resolveArgumentWrappedAsOptional() throws Exception {
		Map<String, String> uriTemplateVars = new HashMap<>();
		uriTemplateVars.put("name", "value");
		request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, uriTemplateVars);

		ConfigurableWebBindingInitializer initializer = new ConfigurableWebBindingInitializer();
		initializer.setConversionService(new DefaultConversionService());
		WebDataBinderFactory binderFactory = new DefaultDataBinderFactory(initializer);

		@SuppressWarnings("unchecked")
		Optional<String> result = (Optional<String>)
				resolver.resolveArgument(paramOptional, mavContainer, webRequest, binderFactory);
		assertEquals("PathVariable not resolved correctly", "value", result.get());

		@SuppressWarnings("unchecked")
		Map<String, Object> pathVars = (Map<String, Object>) request.getAttribute(View.PATH_VARIABLES);
		assertNotNull(pathVars);
		assertEquals(1, pathVars.size());
		assertEquals(Optional.of("value"), pathVars.get("name"));
	}
