	@Test
	public void parseWebSocketExtensions() {
		List<String> extensions = new ArrayList<>();
		extensions.add("x-foo-extension, x-bar-extension");
		extensions.add("x-test-extension");
		this.headers.put(WebSocketHttpHeaders.SEC_WEBSOCKET_EXTENSIONS, extensions);

		List<WebSocketExtension> parsedExtensions = this.headers.getSecWebSocketExtensions();
		assertThat(parsedExtensions, Matchers.hasSize(3));
	}
