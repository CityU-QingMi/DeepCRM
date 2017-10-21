	@Test
	public void fromTextMessageAsObject() throws Exception {
		TextMessage textMessageMock = mock(TextMessage.class);
		Map<String, String> unmarshalled = Collections.singletonMap("foo", "bar");

		String text = "{\"foo\":\"bar\"}";
		given(textMessageMock.getStringProperty("__typeid__")).willReturn(Object.class.getName());
		given(textMessageMock.getText()).willReturn(text);

		Object result = converter.fromMessage(textMessageMock);
		assertEquals("Invalid result", result, unmarshalled);
	}
