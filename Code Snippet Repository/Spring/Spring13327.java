	@Test
	public void testPathToServlet() throws Exception {
		request.setContextPath("/app");
		request.setServletPath("/servlet");
		RequestContext context = new RequestContext(request, response, servletContext, model);

		assertEquals("/app/servlet", context.getPathToServlet());

		request.setAttribute(WebUtils.FORWARD_CONTEXT_PATH_ATTRIBUTE, "/origApp");
		request.setAttribute(WebUtils.FORWARD_SERVLET_PATH_ATTRIBUTE, "/origServlet");

		assertEquals("/origApp/origServlet", context.getPathToServlet());
	}
