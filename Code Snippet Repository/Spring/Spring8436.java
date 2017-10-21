	@Test
	public void verifyRootWacSupport() {
		assertEquals("foo", foo);
		assertEquals("bar", bar);

		ApplicationContext parent = wac.getParent();
		assertNotNull(parent);
		assertTrue(parent instanceof WebApplicationContext);
		WebApplicationContext root = (WebApplicationContext) parent;
		assertFalse(root.getBeansOfType(String.class).containsKey("bar"));

		ServletContext childServletContext = wac.getServletContext();
		assertNotNull(childServletContext);
		ServletContext rootServletContext = root.getServletContext();
		assertNotNull(rootServletContext);
		assertSame(childServletContext, rootServletContext);

		assertSame(root, rootServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		assertSame(root, childServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
	}
