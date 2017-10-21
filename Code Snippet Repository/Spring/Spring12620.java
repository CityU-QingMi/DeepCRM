	@Test
	public void testBeanDecoration() throws Exception {
		loadBeanDefinitions("mvc-config-bean-decoration.xml");

		RequestMappingHandlerMapping mapping = appContext.getBean(RequestMappingHandlerMapping.class);
		assertNotNull(mapping);
		mapping.setDefaultHandler(handlerMethod);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/");

		HandlerExecutionChain chain = mapping.getHandler(request);
		assertEquals(3, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[0] instanceof ConversionServiceExposingInterceptor);
		assertTrue(chain.getInterceptors()[1] instanceof LocaleChangeInterceptor);
		assertTrue(chain.getInterceptors()[2] instanceof ThemeChangeInterceptor);
		LocaleChangeInterceptor interceptor = (LocaleChangeInterceptor) chain.getInterceptors()[1];
		assertEquals("lang", interceptor.getParamName());
		ThemeChangeInterceptor interceptor2 = (ThemeChangeInterceptor) chain.getInterceptors()[2];
		assertEquals("style", interceptor2.getParamName());
	}
