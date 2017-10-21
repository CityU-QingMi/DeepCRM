	@Test
	@SuppressWarnings("")
	public void handlerExceptionResolver() throws Exception {
		ApplicationContext context = initContext(WebConfig.class);
		HandlerExceptionResolverComposite compositeResolver =
				context.getBean("handlerExceptionResolver", HandlerExceptionResolverComposite.class);

		assertEquals(0, compositeResolver.getOrder());
		List<HandlerExceptionResolver> expectedResolvers = compositeResolver.getExceptionResolvers();

		assertEquals(ExceptionHandlerExceptionResolver.class, expectedResolvers.get(0).getClass());
		assertEquals(ResponseStatusExceptionResolver.class, expectedResolvers.get(1).getClass());
		assertEquals(DefaultHandlerExceptionResolver.class, expectedResolvers.get(2).getClass());

		ExceptionHandlerExceptionResolver eher = (ExceptionHandlerExceptionResolver) expectedResolvers.get(0);
		assertNotNull(eher.getApplicationContext());

		DirectFieldAccessor fieldAccessor = new DirectFieldAccessor(eher);
		List<Object> interceptors = (List<Object>) fieldAccessor.getPropertyValue("responseBodyAdvice");
		assertEquals(1, interceptors.size());
		assertEquals(JsonViewResponseBodyAdvice.class, interceptors.get(0).getClass());

		LocaleContextHolder.setLocale(Locale.ENGLISH);
		try {
			ResponseStatusExceptionResolver rser = (ResponseStatusExceptionResolver) expectedResolvers.get(1);
			MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
			MockHttpServletResponse response = new MockHttpServletResponse();
			rser.resolveException(request, response, context.getBean(TestController.class), new UserAlreadyExistsException());
			assertEquals("User already exists!", response.getErrorMessage());
		}
		finally {
			LocaleContextHolder.resetLocaleContext();
		}
	}
