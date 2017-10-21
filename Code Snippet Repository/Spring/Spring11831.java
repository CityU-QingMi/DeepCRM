	@Test
	public void resolveMultiValueMapArgument() throws Exception {
		MethodParameter param = this.testMethod.annotPresent(RequestParam.class).arg(MultiValueMap.class);
		MockServerHttpRequest request = MockServerHttpRequest.get("/path?foo=bar&foo=baz").build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		Object result= resolve(param, exchange);

		assertTrue(result instanceof MultiValueMap);
		assertEquals(Collections.singletonMap("foo", Arrays.asList("bar", "baz")), result);
	}
