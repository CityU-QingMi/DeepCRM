	@Test
	public void exceptionInInvocation() {
		javax.jms.Message message = new StubTextMessage("foo");
		Session session = mock(Session.class);
		MessagingMessageListenerAdapter listener = getSimpleInstance("wrongParam", Integer.class);

		try {
			listener.onMessage(message, session);
			fail("Should have thrown an exception");
		}
		catch (JMSException ex) {
			fail("Should not have thrown a JMS exception");
		}
		catch (ListenerExecutionFailedException ex) {
			assertEquals(MessageConversionException.class, ex.getCause().getClass());
		}
	}
