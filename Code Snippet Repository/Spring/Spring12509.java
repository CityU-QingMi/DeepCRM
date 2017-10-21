	@Test
	public void testContextLoaderListenerWithDefaultContext() {
		MockServletContext sc = new MockServletContext("");
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"/org/springframework/web/context/WEB-INF/applicationContext.xml " +
				"/org/springframework/web/context/WEB-INF/context-addition.xml");
		ServletContextListener listener = new ContextLoaderListener();
		ServletContextEvent event = new ServletContextEvent(sc);
		listener.contextInitialized(event);
		WebApplicationContext context = (WebApplicationContext) sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		assertTrue("Correct WebApplicationContext exposed in ServletContext", context instanceof XmlWebApplicationContext);
		assertTrue(WebApplicationContextUtils.getRequiredWebApplicationContext(sc) instanceof XmlWebApplicationContext);
		LifecycleBean lb = (LifecycleBean) context.getBean("lifecycle");
		assertTrue("Has father", context.containsBean("father"));
		assertTrue("Has rod", context.containsBean("rod"));
		assertTrue("Has kerry", context.containsBean("kerry"));
		assertTrue("Not destroyed", !lb.isDestroyed());
		assertFalse(context.containsBean("beans1.bean1"));
		assertFalse(context.containsBean("beans1.bean2"));
		listener.contextDestroyed(event);
		assertTrue("Destroyed", lb.isDestroyed());
		assertNull(sc.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE));
		assertNull(WebApplicationContextUtils.getWebApplicationContext(sc));
	}
