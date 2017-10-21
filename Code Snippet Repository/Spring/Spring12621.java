	@Test
	public void testViewControllersOnWebSphere() throws Exception {
		loadBeanDefinitions("mvc-config-view-controllers.xml");

		SimpleUrlHandlerMapping mapping2 = appContext.getBean(SimpleUrlHandlerMapping.class);
		SimpleControllerHandlerAdapter adapter = appContext.getBean(SimpleControllerHandlerAdapter.class);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		request.setRequestURI("/myapp/app/bar");
		request.setContextPath("/myapp");
		request.setServletPath("/app/");
		request.setAttribute("com.ibm.websphere.servlet.uri_non_decoded", "/myapp/app/bar");
		HandlerExecutionChain chain = mapping2.getHandler(request);
		assertEquals(4, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[1] instanceof ConversionServiceExposingInterceptor);
		assertTrue(chain.getInterceptors()[2] instanceof LocaleChangeInterceptor);
		assertTrue(chain.getInterceptors()[3] instanceof ThemeChangeInterceptor);
		ModelAndView mv2 = adapter.handle(request, new MockHttpServletResponse(), chain.getHandler());
		assertEquals("baz", mv2.getViewName());

		request.setRequestURI("/myapp/app/");
		request.setContextPath("/myapp");
		request.setServletPath("/app/");
		chain = mapping2.getHandler(request);
		assertEquals(4, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[1] instanceof ConversionServiceExposingInterceptor);
		assertTrue(chain.getInterceptors()[2] instanceof LocaleChangeInterceptor);
		assertTrue(chain.getInterceptors()[3] instanceof ThemeChangeInterceptor);
		ModelAndView mv3 = adapter.handle(request, new MockHttpServletResponse(), chain.getHandler());
		assertEquals("root", mv3.getViewName());

		request.setRequestURI("/myapp/");
		request.setContextPath("/myapp");
		request.setServletPath("/");
		chain = mapping2.getHandler(request);
		assertEquals(4, chain.getInterceptors().length);
		assertTrue(chain.getInterceptors()[1] instanceof ConversionServiceExposingInterceptor);
		assertTrue(chain.getInterceptors()[2] instanceof LocaleChangeInterceptor);
		assertTrue(chain.getInterceptors()[3] instanceof ThemeChangeInterceptor);
		mv3 = adapter.handle(request, new MockHttpServletResponse(), chain.getHandler());
		assertEquals("root", mv3.getViewName());
	}
