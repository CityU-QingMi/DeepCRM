	@Test
	public void getMatchable() throws Exception {
		MutablePropertyValues pvs = new MutablePropertyValues(
				Collections.singletonMap("urlMap", Collections.singletonMap("/path", new Object())));

		StaticWebApplicationContext cxt = new StaticWebApplicationContext();
		cxt.registerSingleton("hm", SimpleUrlHandlerMapping.class, pvs);
		cxt.refresh();

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/path");
		MatchableHandlerMapping hm = getIntrospector(cxt).getMatchableHandlerMapping(request);

		assertEquals(cxt.getBean("hm"), hm);
		assertNull("Attributes changes not ignored", request.getAttribute(BEST_MATCHING_PATTERN_ATTRIBUTE));
	}
