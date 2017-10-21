	@Test
	public void getHandlerMappedInterceptors() throws Exception {
		String path = "/foo";
		HandlerInterceptor interceptor = new HandlerInterceptorAdapter() {};
		MappedInterceptor mappedInterceptor = new MappedInterceptor(new String[] {path}, interceptor);

		TestRequestMappingInfoHandlerMapping mapping = new TestRequestMappingInfoHandlerMapping();
		mapping.registerHandler(new TestController());
		mapping.setInterceptors(new Object[] { mappedInterceptor });
		mapping.setApplicationContext(new StaticWebApplicationContext());

		HandlerExecutionChain chain = mapping.getHandler(new MockHttpServletRequest("GET", path));
		assertNotNull(chain);
		assertNotNull(chain.getInterceptors());
		assertSame(interceptor, chain.getInterceptors()[0]);

		chain = mapping.getHandler(new MockHttpServletRequest("GET", "/invalid"));
		assertNull(chain);
	}
