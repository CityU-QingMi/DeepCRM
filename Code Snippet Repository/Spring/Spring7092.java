	@Test
	public void toTextMessageWithMap() throws Exception {
		converter.setTargetType(MessageType.TEXT);
		TextMessage textMessageMock = mock(TextMessage.class);
		Map<String, String> toBeMarshalled = new HashMap<>();
		toBeMarshalled.put("foo", "bar");

		given(sessionMock.createTextMessage(isA(String.class))).willReturn(textMessageMock);

		converter.toMessage(toBeMarshalled, sessionMock);
		verify(textMessageMock).setStringProperty("__typeid__", HashMap.class.getName());
	}
