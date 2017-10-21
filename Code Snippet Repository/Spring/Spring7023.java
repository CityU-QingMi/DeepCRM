	@Test
	public void testWithResponsiveMessageDelegateWhenReturnTypeIsNotAJMSMessageAndNoMessageConverterIsSupplied() throws Exception {
		final TextMessage sentTextMessage = mock(TextMessage.class);
		final Session session = mock(Session.class);
		ResponsiveMessageDelegate delegate = mock(ResponsiveMessageDelegate.class);
		given(delegate.handleMessage(sentTextMessage)).willReturn(RESPONSE_TEXT);

		final MessageListenerAdapter adapter = new MessageListenerAdapter(delegate) {
			@Override
			protected Object extractMessage(Message message) {
				return message;
			}
		};
		adapter.setMessageConverter(null);
		try {
			adapter.onMessage(sentTextMessage, session);
			fail("expected CouldNotSendReplyException with MessageConversionException");
		}
		catch (ReplyFailureException ex) {
			assertEquals(MessageConversionException.class, ex.getCause().getClass());
		}
	}
