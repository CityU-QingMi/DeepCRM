	@Test
	public void simpValidatorCustom() {
		final Validator validator = mock(Validator.class);
		AbstractMessageBrokerConfiguration config = new BaseTestMessageBrokerConfig() {
			@Override
			public Validator getValidator() {
				return validator;
			}
		};

		assertSame(validator, config.simpValidator());
	}
