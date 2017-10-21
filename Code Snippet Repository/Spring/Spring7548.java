	@Test
	public void configureMessageConvertersCustom() {
		final MessageConverter testConverter = mock(MessageConverter.class);
		AbstractMessageBrokerConfiguration config = new BaseTestMessageBrokerConfig() {
			@Override
			protected boolean configureMessageConverters(List<MessageConverter> messageConverters) {
				messageConverters.add(testConverter);
				return false;
			}
		};

		CompositeMessageConverter compositeConverter = config.brokerMessageConverter();
		assertThat(compositeConverter.getConverters().size(), Matchers.is(1));
		Iterator<MessageConverter> iterator = compositeConverter.getConverters().iterator();
		assertThat(iterator.next(), Matchers.is(testConverter));
	}
