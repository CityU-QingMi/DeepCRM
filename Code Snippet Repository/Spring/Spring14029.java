	@Test
	public void parseHeaderSingle() {
		List<WebSocketExtension> extensions = WebSocketExtension.parseExtensions("x-test-extension ; foo=bar ; bar=baz");
		assertThat(extensions, Matchers.hasSize(1));
		WebSocketExtension extension = extensions.get(0);

		assertEquals("x-test-extension", extension.getName());
		assertEquals(2, extension.getParameters().size());
		assertEquals("bar", extension.getParameters().get("foo"));
		assertEquals("baz", extension.getParameters().get("bar"));
	}
