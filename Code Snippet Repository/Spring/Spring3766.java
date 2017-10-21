	@Test
	public void isDefaultJndiEnvironmentAvailableFalse() throws Exception {
		Field builderField = NamingManager.class.getDeclaredField("initctx_factory_builder");
		builderField.setAccessible(true);
		Object oldBuilder = builderField.get(null);
		builderField.set(null, null);

		try {
			assertThat(JndiLocatorDelegate.isDefaultJndiEnvironmentAvailable(), equalTo(false));
		}
		finally {
			builderField.set(null, oldBuilder);
		}
	}
