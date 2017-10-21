	private void testUrl(String url, Object bean, HandlerMapping handlerMapping, String pathWithinMapping) {
		MockServerHttpRequest request = MockServerHttpRequest.method(HttpMethod.GET, URI.create(url)).build();
		ServerWebExchange exchange = MockServerWebExchange.from(request);
		Object actual = handlerMapping.getHandler(exchange).block();
		if (bean != null) {
			assertNotNull(actual);
			assertSame(bean, actual);
			//noinspection OptionalGetWithoutIsPresent
			PathContainer path = exchange.getAttribute(PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
			assertNotNull(path);
			assertEquals(pathWithinMapping, path.value());
		}
		else {
			assertNull(actual);
		}
	}
