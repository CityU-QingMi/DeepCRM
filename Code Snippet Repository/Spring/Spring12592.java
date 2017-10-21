	@Test
	public void contextInitializerClasses() throws Exception {
		DispatcherServlet servlet = new DispatcherServlet();
		servlet.setContextClass(SimpleWebApplicationContext.class);
		servlet.setContextInitializerClasses(
				TestWebContextInitializer.class.getName() + "," + OtherWebContextInitializer.class.getName());
		servlet.init(servletConfig);
		assertEquals("true", getServletContext().getAttribute("initialized"));
		assertEquals("true", getServletContext().getAttribute("otherInitialized"));
	}
