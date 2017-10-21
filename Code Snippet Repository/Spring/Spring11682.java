	private void testHttpMediaTypeNotSupportedException(String url) throws Exception {
		MockServerHttpRequest request = put(url).contentType(MediaType.APPLICATION_JSON).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		Mono<Object> mono = this.handlerMapping.getHandler(exchange);

		assertError(mono, UnsupportedMediaTypeStatusException.class, ex ->
				assertEquals("Invalid supported consumable media types",
						Collections.singletonList(new MediaType("application", "xml")),
						ex.getSupportedMediaTypes()));
	}
