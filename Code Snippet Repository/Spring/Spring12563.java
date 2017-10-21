	@Test
	public void simpleMappingExceptionResolverWithAllHandlers2() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/loc.do");
		request.addPreferredLocale(Locale.CANADA);
		request.addUserRole("role1");
		request.addParameter("servlet", "yes");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals(500, response.getStatus());
		assertEquals("forwarded to failed", "failed1.jsp", response.getForwardedUrl());
		assertTrue("Exception exposed", request.getAttribute("exception") instanceof ServletException);
	}
