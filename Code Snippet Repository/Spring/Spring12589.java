	@Test
	public void environmentOperations() {
		DispatcherServlet servlet = new DispatcherServlet();
		ConfigurableEnvironment defaultEnv = servlet.getEnvironment();
		assertThat(defaultEnv, notNullValue());
		ConfigurableEnvironment env1 = new StandardServletEnvironment();
		servlet.setEnvironment(env1); // should succeed
		assertThat(servlet.getEnvironment(), sameInstance(env1));
		try {
			servlet.setEnvironment(new DummyEnvironment());
			fail("expected IllegalArgumentException for non-configurable Environment");
		}
		catch (IllegalArgumentException ex) {
		}
		class CustomServletEnvironment extends StandardServletEnvironment { }
		@SuppressWarnings("serial")
		DispatcherServlet custom = new DispatcherServlet() {
			@Override
			protected ConfigurableWebEnvironment createEnvironment() {
				return new CustomServletEnvironment();
			}
		};
		assertThat(custom.getEnvironment(), instanceOf(CustomServletEnvironment.class));
	}
