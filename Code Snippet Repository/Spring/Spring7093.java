	@Test
	public void fromTextMessage() throws Exception {
		TextMessage textMessageMock = mock(TextMessage.class);
		MyBean unmarshalled = new MyBean("bar");

		String text = "{\"foo\":\"bar\"}";
		given(textMessageMock.getStringProperty("__typeid__")).willReturn(MyBean.class.getName());
		given(textMessageMock.getText()).willReturn(text);

		MyBean result = (MyBean)converter.fromMessage(textMessageMock);
		assertEquals("Invalid result", result, unmarshalled);
	}
