	@Test
	public void exceptionInListener() {
		javax.jms.Message message = new StubTextMessage("foo");
		Session session = mock(Session.class);
		MessagingMessageListenerAdapter listener = getSimpleInstance("fail", String.class);

		try {
			listener.onMessage(message, session);
			fail("Should have thrown an exception");
		}
		catch (JMSException ex) {
			fail("Should not have thrown a JMS exception");
		}
		catch (ListenerExecutionFailedException ex) {
			assertEquals(IllegalArgumentException.class, ex.getCause().getClass());
			assertEquals("Expected test exception", ex.getCause().getMessage());
		}
	}
