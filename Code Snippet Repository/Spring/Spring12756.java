	@Test
	public void multiLevelWithMapping() throws Exception {
		UrlFilenameViewController ctrl = new UrlFilenameViewController();
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/docs/cvs/commit.html");
		exposePathInMapping(request, "/docs/**");
		MockHttpServletResponse response = new MockHttpServletResponse();
		ModelAndView mv = ctrl.handleRequest(request, response);
		assertEquals("cvs/commit", mv.getViewName());
		assertTrue(mv.getModel().isEmpty());
	}
