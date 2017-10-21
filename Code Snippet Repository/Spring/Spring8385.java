	@Test
	public void registerAndUnregisterNamedDispatcher() throws Exception {
		final String name = "test-servlet";
		final String url = "/test";
		assertNull(sc.getNamedDispatcher(name));

		sc.registerNamedDispatcher(name, new MockRequestDispatcher(url));
		RequestDispatcher namedDispatcher = sc.getNamedDispatcher(name);
		assertNotNull(namedDispatcher);
		MockHttpServletResponse response = new MockHttpServletResponse();
		namedDispatcher.forward(new MockHttpServletRequest(sc), response);
		assertEquals(url, response.getForwardedUrl());

		sc.unregisterNamedDispatcher(name);
		assertNull(sc.getNamedDispatcher(name));
	}
