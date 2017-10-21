	@Test
	public void toTextMessageWithAnotherJsonViewClass() throws JMSException {
		converter.setTargetType(MessageType.TEXT);
		TextMessage textMessageMock = mock(TextMessage.class);

		MyAnotherBean bean = new MyAnotherBean("test", "lengthy description");
		given(sessionMock.createTextMessage(isA(String.class))).willReturn(textMessageMock);


		converter.toMessage(bean, sessionMock, Full.class);
		verify(textMessageMock).setStringProperty("__typeid__", MyAnotherBean.class.getName());
		verify(sessionMock).createTextMessage("{\"name\":\"test\",\"description\":\"lengthy description\"}");
	}
