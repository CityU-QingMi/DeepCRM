	@Test
	public void testInterceptors() throws Exception {
		loadBeanDefinitions("mvc-config-interceptors.xml");

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(mapping);
		mapping.setDefaultHandler(handlerMethod);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");
		request.setRequestURI("/accounts/12345");
		request.addParameter("locale", "en");
		request.addParameter("theme", "green");

		HandlerExecutionChain chain = mapping.getHandler(request);
		assertEquals(4, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[0] instanceof ConversionServiceExposingInterceptor);
		assertTrue(chain.getInterceptors()[1] instanceof LocaleChangeInterceptor);
		assertTrue(chain.getInterceptors()[2] instanceof ThemeChangeInterceptor);
		assertTrue(chain.getInterceptors()[3] instanceof UserRoleAuthorizationInterceptor);

		request.setRequestURI("/admin/users");
		chain = mapping.getHandler(request);
		assertEquals(2, chain.getInterceptors().length);

		request.setRequestURI("/logged/accounts/12345");
		chain = mapping.getHandler(request);
		assertEquals(3, chain.getInterceptors().length);

		request.setRequestURI("/foo/logged");
		chain = mapping.getHandler(request);
		assertEquals(3, chain.getInterceptors().length);
	}
