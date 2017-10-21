	@Test
	public void useDefaultCharset() throws Exception {
		this.exchange.getAttributes().put(PRODUCIBLE_MEDIA_TYPES_ATTRIBUTE,
				Collections.singleton(APPLICATION_JSON));

		String body = "foo";
		MethodParameter type = on(TestController.class).resolveReturnType(String.class);
		this.resultHandler.writeBody(body, type, this.exchange).block(Duration.ofSeconds(5));

		assertEquals(APPLICATION_JSON_UTF8, this.exchange.getResponse().getHeaders().getContentType());
	}
