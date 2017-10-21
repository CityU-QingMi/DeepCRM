	@Test
	public void configureMessageConvertersCustomAndDefault() {
		final MessageConverter testConverter = mock(MessageConverter.class);

		AbstractMessageBrokerConfiguration config = new BaseTestMessageBrokerConfig() {
			@Override
			protected boolean configureMessageConverters(List<MessageConverter> messageConverters) {
				messageConverters.add(testConverter);
				return true;
			}
		};
		CompositeMessageConverter compositeConverter = config.brokerMessageConverter();

		assertThat(compositeConverter.getConverters().size(), Matchers.is(4));
		Iterator<MessageConverter> iterator = compositeConverter.getConverters().iterator();
		assertThat(iterator.next(), Matchers.is(testConverter));
		assertThat(iterator.next(), Matchers.instanceOf(StringMessageConverter.class));
		assertThat(iterator.next(), Matchers.instanceOf(ByteArrayMessageConverter.class));
		assertThat(iterator.next(), Matchers.instanceOf(MappingJackson2MessageConverter.class));
	}
