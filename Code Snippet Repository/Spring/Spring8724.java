	private void verifyRootWacSupport() {
		assertNotNull(personDao);
		assertNotNull(personController);

		ApplicationContext parent = wac.getParent();
		assertNotNull(parent);
		assertTrue(parent instanceof WebApplicationContext);
		WebApplicationContext root = (WebApplicationContext) parent;

		ServletContext childServletContext = wac.getServletContext();
		assertNotNull(childServletContext);
		ServletContext rootServletContext = root.getServletContext();
		assertNotNull(rootServletContext);
		assertSame(childServletContext, rootServletContext);

		assertSame(root, rootServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		assertSame(root, childServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
	}
