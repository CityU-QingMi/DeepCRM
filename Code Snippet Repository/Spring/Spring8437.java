	@Test
	public void verifyDispatcherWacConfig() {
		ApplicationContext parent = wac.getParent();
		assertNotNull(parent);
		assertTrue(parent instanceof WebApplicationContext);

		ApplicationContext grandParent = parent.getParent();
		assertNotNull(grandParent);
		assertFalse(grandParent instanceof WebApplicationContext);

		ServletContext dispatcherServletContext = wac.getServletContext();
		assertNotNull(dispatcherServletContext);
		ServletContext rootServletContext = ((WebApplicationContext) parent).getServletContext();
		assertNotNull(rootServletContext);
		assertSame(dispatcherServletContext, rootServletContext);

		assertSame(parent,
			rootServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		assertSame(parent,
			dispatcherServletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));

		assertEquals("ear", ear);
		assertEquals("root", root);
		assertEquals("dispatcher", dispatcher);
	}
