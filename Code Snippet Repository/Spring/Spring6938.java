	@Test
	public void resolveObjectPayload() throws JMSException {
		MessagingMessageListenerAdapter listener = createDefaultInstance(MyBean.class);
		MyBean myBean = new MyBean();
		myBean.name = "myBean name";

		Session session = mock(Session.class);
		ObjectMessage message = mock(ObjectMessage.class);
		given(message.getObject()).willReturn(myBean);

		listener.onMessage(message, session);
		assertDefaultListenerMethodInvocation();
	}
