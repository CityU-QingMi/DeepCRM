	@Test
	public void propertySourceOrder() throws Exception {
		SimpleNamingContextBuilder.emptyActivatedContextBuilder();

		ConfigurableEnvironment env = new StandardServletEnvironment();
		MutablePropertySources sources = env.getPropertySources();

		assertThat(sources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONFIG_PROPERTY_SOURCE_NAME)), equalTo(0));
		assertThat(sources.precedenceOf(PropertySource.named(StandardServletEnvironment.SERVLET_CONTEXT_PROPERTY_SOURCE_NAME)), equalTo(1));
		assertThat(sources.precedenceOf(PropertySource.named(StandardServletEnvironment.JNDI_PROPERTY_SOURCE_NAME)), equalTo(2));
		assertThat(sources.precedenceOf(PropertySource.named(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME)), equalTo(3));
		assertThat(sources.precedenceOf(PropertySource.named(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME)), equalTo(4));
		assertThat(sources.size(), is(5));
	}
