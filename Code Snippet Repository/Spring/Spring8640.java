	@Test
	public void adaptResponse() throws Exception {

		ResponseCookie cookie = ResponseCookie.from("custom-cookie", "c0").build();

		TestHttpHandler handler = new TestHttpHandler(response -> {
			response.setStatusCode(HttpStatus.OK);
			response.getHeaders().put("custom-header", Arrays.asList("h0", "h1"));
			response.addCookie(cookie);
			return response.writeWith(Mono.just(toDataBuffer("Custom body")));
		});

		ClientHttpResponse response = new HttpHandlerConnector(handler)
				.connect(HttpMethod.GET, URI.create("/custom-path"), ReactiveHttpOutputMessage::setComplete)
				.block(Duration.ofSeconds(5));

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Arrays.asList("h0", "h1"), response.getHeaders().get("custom-header"));
		assertEquals(cookie, response.getCookies().getFirst("custom-cookie"));

		DataBuffer buffer = response.getBody().blockFirst(Duration.ZERO);
		assertEquals("Custom body", DataBufferTestUtils.dumpString(buffer, UTF_8));
	}
