	@Test
	public void annotationMethodMessageHandler() {
		loadBeanDefinitions("websocket-config-broker-simple.xml");

		SimpAnnotationMethodMessageHandler annotationMethodMessageHandler =
				this.appContext.getBean(SimpAnnotationMethodMessageHandler.class);

		assertNotNull(annotationMethodMessageHandler);
		MessageConverter messageConverter = annotationMethodMessageHandler.getMessageConverter();
		assertNotNull(messageConverter);
		assertTrue(messageConverter instanceof CompositeMessageConverter);

		String name = MessageBrokerBeanDefinitionParser.MESSAGE_CONVERTER_BEAN_NAME;
		CompositeMessageConverter compositeMessageConverter = this.appContext.getBean(name, CompositeMessageConverter.class);
		assertNotNull(compositeMessageConverter);

		name = MessageBrokerBeanDefinitionParser.MESSAGING_TEMPLATE_BEAN_NAME;
		SimpMessagingTemplate simpMessagingTemplate = this.appContext.getBean(name, SimpMessagingTemplate.class);
		assertNotNull(simpMessagingTemplate);
		assertEquals("/personal/", simpMessagingTemplate.getUserDestinationPrefix());

		List<MessageConverter> converters = compositeMessageConverter.getConverters();
		assertThat(converters.size(), Matchers.is(3));
		assertThat(converters.get(0), Matchers.instanceOf(StringMessageConverter.class));
		assertThat(converters.get(1), Matchers.instanceOf(ByteArrayMessageConverter.class));
		assertThat(converters.get(2), Matchers.instanceOf(MappingJackson2MessageConverter.class));

		ContentTypeResolver resolver = ((MappingJackson2MessageConverter) converters.get(2)).getContentTypeResolver();
		assertEquals(MimeTypeUtils.APPLICATION_JSON, ((DefaultContentTypeResolver) resolver).getDefaultMimeType());

		DirectFieldAccessor handlerAccessor = new DirectFieldAccessor(annotationMethodMessageHandler);
		String pathSeparator = (String) new DirectFieldAccessor(handlerAccessor.getPropertyValue("pathMatcher")).getPropertyValue("pathSeparator");
		assertEquals(".", pathSeparator);
	}
