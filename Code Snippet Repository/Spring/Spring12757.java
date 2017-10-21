	@Test
	public void multiLevelMappingWithFallback() throws Exception {
		UrlFilenameViewController ctrl = new UrlFilenameViewController();
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/docs/cvs/commit.html");
		exposePathInMapping(request, "/docs/cvs/commit.html");
		MockHttpServletResponse response = new MockHttpServletResponse();
		ModelAndView mv = ctrl.handleRequest(request, response);
		assertEquals("docs/cvs/commit", mv.getViewName());
		assertTrue(mv.getModel().isEmpty());
	}
