	@Test
	@SuppressWarnings("")
	public void readUntyped() throws IOException {
		String body =
				"{\"bytes\":\"AQI=\",\"array\":[\"Foo\",\"Bar\"],\"number\":42,\"string\":\"Foo\",\"bool\":true,\"fraction\":42.0}";
		MockHttpInputMessage inputMessage = new MockHttpInputMessage(body.getBytes("UTF-8"));
		inputMessage.getHeaders().setContentType(new MediaType("application", "json"));
		HashMap<String, Object> result = (HashMap<String, Object>) converter.read(HashMap.class, inputMessage);
		assertEquals("Foo", result.get("string"));
		assertEquals(42, result.get("number"));
		assertEquals(42D, (Double) result.get("fraction"), 0D);
		List<String> array = new ArrayList<>();
		array.add("Foo");
		array.add("Bar");
		assertEquals(array, result.get("array"));
		assertEquals(Boolean.TRUE, result.get("bool"));
		assertEquals("AQI=", result.get("bytes"));
	}
