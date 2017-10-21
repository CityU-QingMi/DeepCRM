	@Test
	public void suffixPatternMatch() throws Exception {
		TestStandaloneMockMvcBuilder builder = new TestStandaloneMockMvcBuilder(new PersonController());
		builder.setUseSuffixPatternMatch(false);
		builder.build();

		RequestMappingHandlerMapping hm = builder.wac.getBean(RequestMappingHandlerMapping.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/persons");
		HandlerExecutionChain chain = hm.getHandler(request);
		assertNotNull(chain);
		assertEquals("persons", ((HandlerMethod) chain.getHandler()).getMethod().getName());

		request = new MockHttpServletRequest("GET", "/persons.xml");
		chain = hm.getHandler(request);
		assertNull(chain);
	}
