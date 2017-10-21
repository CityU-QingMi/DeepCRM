	@Test
	public void handleNoHandlerFoundException() throws Exception {
		ServletServerHttpRequest req = new ServletServerHttpRequest(
				new MockHttpServletRequest("GET","/resource"));
		NoHandlerFoundException ex = new NoHandlerFoundException(req.getMethod().name(),
				req.getServletRequest().getRequestURI(),req.getHeaders());
		ModelAndView mav = exceptionResolver.resolveException(request, response, null, ex);
		assertNotNull("No ModelAndView returned", mav);
		assertTrue("No Empty ModelAndView returned", mav.isEmpty());
		assertEquals("Invalid status code", 404, response.getStatus());
	}
