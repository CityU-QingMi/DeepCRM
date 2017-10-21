	@Test
	public void simpValidatorMvc() {
		StaticApplicationContext appCxt = new StaticApplicationContext();
		appCxt.registerSingleton("mvcValidator", TestValidator.class);
		AbstractMessageBrokerConfiguration config = new BaseTestMessageBrokerConfig() {};
		config.setApplicationContext(appCxt);

		assertThat(config.simpValidator(), Matchers.notNullValue());
		assertThat(config.simpValidator(), Matchers.instanceOf(TestValidator.class));
	}
