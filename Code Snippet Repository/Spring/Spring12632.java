	@Test
	public void testResources() throws Exception {
		loadBeanDefinitions("mvc-config-resources.xml");

		HttpRequestHandlerAdapter adapter = appContext.getBean(HttpRequestHandlerAdapter.class);
		assertNotNull(adapter);

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		ContentNegotiationManager manager = mapping.getContentNegotiationManager();

		ResourceHttpRequestHandler handler = appContext.getBean(ResourceHttpRequestHandler.class);
		assertNotNull(handler);
		assertSame(manager, handler.getContentNegotiationManager());

		SimpleUrlHandlerMapping resourceMapping = appContext.getBean(SimpleUrlHandlerMapping.class);
		assertNotNull(resourceMapping);
		assertEquals(Ordered.LOWEST_PRECEDENCE - 1, resourceMapping.getOrder());

		BeanNameUrlHandlerMapping beanNameMapping = appContext.getBean(BeanNameUrlHandlerMapping.class);
		assertNotNull(beanNameMapping);
		assertEquals(2, beanNameMapping.getOrder());

		ResourceUrlProvider urlProvider = appContext.getBean(ResourceUrlProvider.class);
		assertNotNull(urlProvider);

		Map<String, MappedInterceptor> beans = appContext.getBeansOfType(MappedInterceptor.class);
		List<Class<?>> interceptors = beans.values().stream()
				.map(mappedInterceptor -> mappedInterceptor.getInterceptor().getClass())
				.collect(Collectors.toList());
		assertThat(interceptors, containsInAnyOrder(ConversionServiceExposingInterceptor.class,
				ResourceUrlProviderExposingInterceptor.class));

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/resources/foo.css");
		request.setMethod("GET");

		HandlerExecutionChain chain = resourceMapping.getHandler(request);
		assertNotNull(chain);
		assertTrue(chain.getHandler() instanceof ResourceHttpRequestHandler);

		MockHttpServletResponse response = new MockHttpServletResponse();
		for (HandlerInterceptor interceptor : chain.getInterceptors()) {
			interceptor.preHandle(request, response, chain.getHandler());
		}
		ModelAndView mv = adapter.handle(request, response, chain.getHandler());
		assertNull(mv);
	}
