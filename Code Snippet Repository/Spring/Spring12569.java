	@Test
	public void configuredDispatcherServlets() {
		assertTrue("Correct namespace",
				("simple" + FrameworkServlet.DEFAULT_NAMESPACE_SUFFIX).equals(simpleDispatcherServlet.getNamespace()));
		assertTrue("Correct attribute", (FrameworkServlet.SERVLET_CONTEXT_PREFIX + "simple").equals(
				simpleDispatcherServlet.getServletContextAttributeName()));
		assertTrue("Context published", simpleDispatcherServlet.getWebApplicationContext() ==
				getServletContext().getAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX + "simple"));

		assertTrue("Correct namespace", "test".equals(complexDispatcherServlet.getNamespace()));
		assertTrue("Correct attribute", (FrameworkServlet.SERVLET_CONTEXT_PREFIX + "complex").equals(
				complexDispatcherServlet.getServletContextAttributeName()));
		assertTrue("Context not published",
				getServletContext().getAttribute(FrameworkServlet.SERVLET_CONTEXT_PREFIX + "complex") == null);

		simpleDispatcherServlet.destroy();
		complexDispatcherServlet.destroy();
	}
