	@Test
	public void resolveArgumentNativeHeaderAmbiguity() throws Exception {
		TestMessageHeaderAccessor headers = new TestMessageHeaderAccessor();
		headers.setHeader("param1", "foo");
		headers.setNativeHeader("param1", "native-foo");
		Message<byte[]> message = MessageBuilder.withPayload(new byte[0]).setHeaders(headers).build();

		assertEquals("foo", this.resolver.resolveArgument(this.paramRequired, message));
		assertEquals("native-foo", this.resolver.resolveArgument(this.paramNativeHeader, message));
	}
