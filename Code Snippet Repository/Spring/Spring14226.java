	@Test
	public void handleInfoGetWildflyNPE() throws Exception {
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		ServletOutputStream ous = mock(ServletOutputStream.class);
		given(mockResponse.getHeaders(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)).willThrow(NullPointerException.class);
		given(mockResponse.getOutputStream()).willReturn(ous);
		this.response = new ServletServerHttpResponse(mockResponse);

		handleRequest("GET", "/echo/info", HttpStatus.OK);

		verify(mockResponse, times(1)).getOutputStream();
	}
