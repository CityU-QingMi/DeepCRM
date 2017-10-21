	@Test
	public void propertyWithColonInNonResourceRefMode() {
		JndiLocatorDelegate jndiLocator = new JndiLocatorDelegate() {
			@Override
			public Object lookup(String jndiName) throws NamingException {
				assertEquals("my:key", jndiName);
				return "my:value";
			}
		};
		jndiLocator.setResourceRef(false);

		JndiPropertySource ps = new JndiPropertySource("jndiProperties", jndiLocator);
		assertThat(ps.getProperty("my:key"), equalTo("my:value"));
	}
