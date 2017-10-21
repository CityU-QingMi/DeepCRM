	@Test
	public void decodeFullContentAsString() {
		String body = "data:foo\ndata:bar\n\ndata:baz\n\n";
		MockServerHttpRequest request = MockServerHttpRequest.post("/").body(body);

		String actual = messageReader
				.readMono(ResolvableType.forClass(String.class), request, Collections.emptyMap())
				.cast(String.class)
				.block(Duration.ZERO);

		assertEquals(body, actual);
	}
