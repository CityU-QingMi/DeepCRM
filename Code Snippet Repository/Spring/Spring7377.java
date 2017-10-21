	@Test
	public void fromMessage() throws Exception {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		String payload = "{\"bytes\":\"AQI=\",\"array\":[\"Foo\",\"Bar\"],\"number\":42,\"string\":\"Foo\",\"bool\":true,\"fraction\":42.0}";
		Message<?> message = MessageBuilder.withPayload(payload.getBytes(StandardCharsets.UTF_8)).build();
		MyBean actual = (MyBean) converter.fromMessage(message, MyBean.class);

		assertEquals("Foo", actual.getString());
		assertEquals(42, actual.getNumber());
		assertEquals(42F, actual.getFraction(), 0F);
		assertArrayEquals(new String[]{"Foo", "Bar"}, actual.getArray());
		assertTrue(actual.isBool());
		assertArrayEquals(new byte[]{0x1, 0x2}, actual.getBytes());
	}
