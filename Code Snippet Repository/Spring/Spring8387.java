	@Test
	public void setDefaultServletName() throws Exception {
		final String originalDefault = "default";
		final String newDefault = "test";
		assertNotNull(sc.getNamedDispatcher(originalDefault));

		sc.setDefaultServletName(newDefault);
		assertEquals(newDefault, sc.getDefaultServletName());
		assertNull(sc.getNamedDispatcher(originalDefault));

		RequestDispatcher namedDispatcher = sc.getNamedDispatcher(newDefault);
		assertNotNull(namedDispatcher);
		MockHttpServletResponse response = new MockHttpServletResponse();
		namedDispatcher.forward(new MockHttpServletRequest(sc), response);
		assertEquals(newDefault, response.getForwardedUrl());
	}
