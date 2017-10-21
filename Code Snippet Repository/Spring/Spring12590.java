	@Test
	public void allowedOptionsIncludesPatchMethod() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest(getServletContext(), "OPTIONS", "/foo");
		MockHttpServletResponse response = spy(new MockHttpServletResponse());
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setDispatchOptionsRequest(false);
		servlet.service(request, response);
		verify(response, never()).getHeader(anyString()); // SPR-10341
		assertThat(response.getHeader("Allow"), equalTo("GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH"));
	}
