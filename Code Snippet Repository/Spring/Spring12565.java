	@Test
	public void localeChangeInterceptor1() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "GET", "/locale.do");
		request.addPreferredLocale(Locale.GERMAN);
		request.addUserRole("role2");
		request.addParameter("locale", "en");
		MockHttpServletResponse response = new MockHttpServletResponse();
		complexDispatcherServlet.service(request, response);
		assertEquals(200, response.getStatus());
		assertEquals("forwarded to failed", "failed0.jsp", response.getForwardedUrl());
		assertTrue("Exception exposed", request.getAttribute("exception").getClass().equals(ServletException.class));
	}
