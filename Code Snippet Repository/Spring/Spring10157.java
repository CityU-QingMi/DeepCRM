	@Test
	@SuppressWarnings("")
	public void readGenerics() throws Exception {
		Field beansList = ListHolder.class.getField("listField");

		String body = "[{\"bytes\":[1,2],\"array\":[\"Foo\",\"Bar\"]," +
				"\"number\":42,\"string\":\"Foo\",\"bool\":true,\"fraction\":42.0}]";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body.getBytes(StandardCharsets.UTF_8));
		inputMessage.getHeaders().setContentType(new MediaType("application", "json"));

		List<MyBean> results = (List<MyBean>) converter.read(beansList.getGenericType(), MyBeanListHolder.class, inputMessage);
		assertEquals(1, results.size());
		MyBean result = results.get(0);
		assertEquals("Foo", result.getString());
		assertEquals(42, result.getNumber());
		assertEquals(42F, result.getFraction(), 0F);
		assertArrayEquals(new String[] { "Foo", "Bar" }, result.getArray());
		assertTrue(result.isBool());
		assertArrayEquals(new byte[] { 0x1, 0x2 }, result.getBytes());
	}
