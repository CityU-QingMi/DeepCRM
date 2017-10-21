	@Test
	public void testApplicationScope() {
		WebApplicationContext ac = initApplicationContext(WebApplicationContext.SCOPE_APPLICATION);
		assertNull(ac.getServletContext().getAttribute(NAME));
		DerivedTestBean bean = ac.getBean(NAME, DerivedTestBean.class);
		assertSame(bean, ac.getServletContext().getAttribute(NAME));
		assertSame(bean, ac.getBean(NAME));
		new ContextCleanupListener().contextDestroyed(new ServletContextEvent(ac.getServletContext()));
		assertTrue(bean.wasDestroyed());
	}
