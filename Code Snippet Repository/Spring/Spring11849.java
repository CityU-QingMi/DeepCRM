	private void assertConditionalResponse(MockServerWebExchange exchange, HttpStatus status,
			String body, String etag, Instant lastModified) throws Exception {

		assertEquals(status, exchange.getResponse().getStatusCode());
		if (body != null) {
			assertResponseBody(exchange, body);
		}
		else {
			assertResponseBodyIsEmpty(exchange);
		}
		if (etag != null) {
			assertEquals(1, exchange.getResponse().getHeaders().get(HttpHeaders.ETAG).size());
			assertEquals(etag, exchange.getResponse().getHeaders().getETag());
		}
		if (lastModified.isAfter(Instant.EPOCH)) {
			assertEquals(1, exchange.getResponse().getHeaders().get(HttpHeaders.LAST_MODIFIED).size());
			assertEquals(lastModified.toEpochMilli(), exchange.getResponse().getHeaders().getLastModified());
		}
	}
