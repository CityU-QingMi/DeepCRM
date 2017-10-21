	@Test
	public void specificStatusCode() {
		exceptionResolver.setDefaultErrorView("default-view");
		exceptionResolver.setDefaultStatusCode(HttpServletResponse.SC_BAD_REQUEST);
		Properties statusCodes = new Properties();
		statusCodes.setProperty("default-view", "406");
		exceptionResolver.setStatusCodes(statusCodes);
		exceptionResolver.resolveException(request, response, handler1, genericException);
		assertEquals(HttpServletResponse.SC_NOT_ACCEPTABLE, response.getStatus());
	}
