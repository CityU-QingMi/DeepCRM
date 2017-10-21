	@Test
	public void setExtraCollaborators() {
		MessageConverter messageConverter = mock(MessageConverter.class);
		DestinationResolver destinationResolver = mock(DestinationResolver.class);
		this.container.setMessageConverter(messageConverter);
		this.container.setDestinationResolver(destinationResolver);

		MessagingMessageListenerAdapter listener = createInstance(this.factory,
				getListenerMethod("resolveObjectPayload", MyBean.class), this.container);
		DirectFieldAccessor accessor = new DirectFieldAccessor(listener);
		assertSame(messageConverter, accessor.getPropertyValue("messageConverter"));
		assertSame(destinationResolver, accessor.getPropertyValue("destinationResolver"));
	}
