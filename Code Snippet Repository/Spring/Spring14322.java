	@Test
	public void registerServletParamPropertySources_StaticWebApplicationContext() {
		MockServletContext servletContext = new MockServletContext();
		servletContext.addInitParameter("pCommon", "pCommonContextValue");
		servletContext.addInitParameter("pContext1", "pContext1Value");

		MockServletConfig servletConfig = new MockServletConfig(servletContext);
		servletConfig.addInitParameter("pCommon", "pCommonConfigValue");
		servletConfig.addInitParameter("pConfig1", "pConfig1Value");

		StaticWebApplicationContext ctx = new StaticWebApplicationContext();
		ctx.setServletConfig(servletConfig);
		ctx.refresh();

		ConfigurableEnvironment environment = ctx.getEnvironment();
		MutablePropertySources propertySources = environment.getPropertySources();
		assertThat(propertySources.contains(StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME), is(true));
		assertThat(propertySources.contains(StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME), is(true));

		// ServletConfig gets precedence
		assertThat(environment.getProperty("pCommon"), is("pCommonConfigValue"));
		assertThat(propertySources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME)),
				lessThan(propertySources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME))));

		// but all params are available
		assertThat(environment.getProperty("pContext1"), is("pContext1Value"));
		assertThat(environment.getProperty("pConfig1"), is("pConfig1Value"));

		// Servlet* PropertySources have precedence over System* PropertySources
		assertThat(propertySources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME)),
				lessThan(propertySources.precedenceOf(PropertySource.named(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME))));

		// Replace system properties with a mock property source for convenience
		MockPropertySource mockSystemProperties = new MockPropertySource(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME);
		mockSystemProperties.setProperty("pCommon", "pCommonSysPropsValue");
		mockSystemProperties.setProperty("pSysProps1", "pSysProps1Value");
		propertySources.replace(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, mockSystemProperties);

		// assert that servletconfig params resolve with higher precedence than sysprops
		assertThat(environment.getProperty("pCommon"), is("pCommonConfigValue"));
		assertThat(environment.getProperty("pSysProps1"), is("pSysProps1Value"));
	}
