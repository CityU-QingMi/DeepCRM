	@Test
	public void registerServletParamPropertySources_GenericWebApplicationContext() {
		MockServletContext servletContext = new MockServletContext();
		servletContext.addInitParameter("pCommon", "pCommonContextValue");
		servletContext.addInitParameter("pContext1", "pContext1Value");

		GenericWebApplicationContext ctx = new GenericWebApplicationContext();
		ctx.setServletContext(servletContext);
		ctx.refresh();

		ConfigurableEnvironment environment = ctx.getEnvironment();
		assertThat(environment, instanceOf(StandardServletEnvironment.class));
		MutablePropertySources propertySources = environment.getPropertySources();
		assertThat(propertySources.contains(StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME), is(true));

		// ServletContext params are available
		assertThat(environment.getProperty("pCommon"), is("pCommonContextValue"));
		assertThat(environment.getProperty("pContext1"), is("pContext1Value"));

		// Servlet* PropertySources have precedence over System* PropertySources
		assertThat(propertySources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME)),
				lessThan(propertySources.precedenceOf(PropertySource.named(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME))));

		// Replace system properties with a mock property source for convenience
		MockPropertySource mockSystemProperties = new MockPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);
		mockSystemProperties.setProperty("pCommon", "pCommonSysPropsValue");
		mockSystemProperties.setProperty("pSysProps1", "pSysProps1Value");
		propertySources.replace(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, mockSystemProperties);

		// assert that servletcontext init params resolve with higher precedence than sysprops
		assertThat(environment.getProperty("pCommon"), is("pCommonContextValue"));
		assertThat(environment.getProperty("pSysProps1"), is("pSysProps1Value"));
	}
