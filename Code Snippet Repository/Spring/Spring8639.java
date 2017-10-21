	@Test
	public void adaptRequest() throws Exception {

		TestHttpHandler handler = new TestHttpHandler(response -> {
			response.setStatusCode(HttpStatus.OK);
			return response.setComplete();
		});

		new HttpHandlerConnector(handler).connect(HttpMethod.POST, URI.create("/custom-path"),
				request -> {
					request.getHeaders().put("custom-header", Arrays.asList("h0", "h1"));
					request.getCookies().add("custom-cookie", new HttpCookie("custom-cookie", "c0"));
					return request.writeWith(Mono.just(toDataBuffer("Custom body")));
				}).block(Duration.ofSeconds(5));

		MockServerHttpRequest request = (MockServerHttpRequest) handler.getSavedRequest();
		assertEquals(HttpMethod.POST, request.getMethod());
		assertEquals("/custom-path", request.getURI().toString());

		assertEquals(Arrays.asList("h0", "h1"), request.getHeaders().get("custom-header"));
		assertEquals(new HttpCookie("custom-cookie", "c0"), request.getCookies().getFirst("custom-cookie"));

		DataBuffer buffer = request.getBody().blockFirst(Duration.ZERO);
		assertEquals("Custom body", DataBufferTestUtils.dumpString(buffer, UTF_8));
	}
