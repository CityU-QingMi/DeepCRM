	@Test
	public void resolveOptionalParamValue() throws Exception {
		ServerWebExchange exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/").build());
		MethodParameter param = this.testMethod.arg(forClassWithGenerics(Optional.class, Integer.class));
		Object result = resolve(param, exchange);
		assertEquals(Optional.empty(), result);

		exchange = MockServerWebExchange.from(MockServerHttpRequest.get("/path?name=123").build());
		result = resolve(param, exchange);

		assertEquals(Optional.class, result.getClass());
		Optional<?> value = (Optional<?>) result;
		assertTrue(value.isPresent());
		assertEquals(123, value.get());
	}
