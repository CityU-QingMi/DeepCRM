	@Test
	public void testContextLoaderListenerWithProgrammaticAndGlobalInitializers() {
		MockServletContext sc = new MockServletContext("");
		sc.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				"org/springframework/web/context/WEB-INF/ContextLoaderTests-acc-context.xml");
		sc.addInitParameter(ContextLoader.GLOBAL_INITIALIZER_CLASSES_PARAM, TestWebContextInitializer.class.getName());
		ContextLoaderListener listener = new ContextLoaderListener();
		listener.setContextInitializers(new TestContextInitializer());
		listener.contextInitialized(new ServletContextEvent(sc));
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
		TestBean testBean = wac.getBean(TestBean.class);
		assertThat(testBean.getName(), equalTo("testName"));
		assertThat(wac.getServletContext().getAttribute("initialized"), notNullValue());
	}
