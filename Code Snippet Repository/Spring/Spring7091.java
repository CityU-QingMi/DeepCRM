	@Test
	public void toTextMessageWithObject() throws Exception {
		converter.setTargetType(MessageType.TEXT);
		TextMessage textMessageMock = mock(TextMessage.class);
		Date toBeMarshalled = new Date();

		given(sessionMock.createTextMessage(isA(String.class))).willReturn(textMessageMock);

		converter.toMessage(toBeMarshalled, sessionMock);
		verify(textMessageMock).setStringProperty("__typeid__", Date.class.getName());
	}
