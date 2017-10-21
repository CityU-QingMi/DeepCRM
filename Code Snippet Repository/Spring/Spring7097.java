	@Test
	public void toTextMessageWithJsonViewClass() throws JMSException {
		converter.setTargetType(MessageType.TEXT);
		TextMessage textMessageMock = mock(TextMessage.class);

		MyAnotherBean bean = new MyAnotherBean("test", "lengthy description");
		given(sessionMock.createTextMessage(isA(String.class))).willReturn(textMessageMock);


		converter.toMessage(bean, sessionMock, Summary.class);
		verify(textMessageMock).setStringProperty("__typeid__", MyAnotherBean.class.getName());
		verify(sessionMock).createTextMessage("{\"name\":\"test\"}");
	}
