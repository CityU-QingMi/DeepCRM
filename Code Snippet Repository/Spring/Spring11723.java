	@Test
	public void requestEntity() throws Exception {
		ServerWebExchange exchange = postExchange("line1");
		ResolvableType type = forClassWithGenerics(RequestEntity.class, String.class);
		RequestEntity<String> requestEntity = resolveValue(exchange, type);

		assertEquals(exchange.getRequest().getMethod(), requestEntity.getMethod());
		assertEquals(exchange.getRequest().getURI(), requestEntity.getUrl());
		assertEquals(exchange.getRequest().getHeaders(), requestEntity.getHeaders());
		assertEquals("line1", requestEntity.getBody());
	}
