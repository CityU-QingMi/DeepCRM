	@Test
	public void simpleMappingExceptionResolverWithSpecificHandler1() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do");
		request.addPreferredLocale(Locale.CANADA);
		request.addUserRole("role1");
		request.addParameter("access", "yes");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals("forwarded to failed", "failed2.jsp", response.getForwardedUrl());
		assertTrue("Exception exposed", request.getAttribute("exception") instanceof IllegalAccessException);
	}
