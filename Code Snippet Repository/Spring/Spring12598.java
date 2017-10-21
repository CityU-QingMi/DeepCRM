	@Test
	public void multipartResolutionFailed() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do;abc=def");
		request.addPreferredLocale(Locale.CANADA);
		request.addUserRole("role1");
		request.setAttribute("fail", Boolean.TRUE);
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertTrue("forwarded to failed", "failed0.jsp".equals(response.getForwardedUrl()));
		assertEquals(200, response.getStatus());
		assertTrue("correct exception", request.getAttribute(
				SimpleMappingExceptionResolver.DEFAULT_EXCEPTION_ATTRIBUTE) instanceof MaxUploadSizeExceededException);
	}
