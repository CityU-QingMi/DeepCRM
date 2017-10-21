	@Test
	public void resolveDestinationForQueue() throws JMSException {
		Session session = mock(Session.class);
		DestinationResolver destinationResolver = mock(DestinationResolver.class);
		Destination destination = mock(Destination.class);

		given(destinationResolver.resolveDestinationName(session, "myQueue", false)).willReturn(destination);
		JmsResponse<String> jmsResponse = JmsResponse.forQueue("foo", "myQueue");
		Destination actual = jmsResponse.resolveDestination(destinationResolver, session);
		assertSame(destination, actual);
	}
