	@Test
	public void partialContentMultipleByteRanges() throws Exception {
		MockServerHttpRequest request = MockServerHttpRequest.get("").header("Range", "bytes=0-1, 4-5, 8-9").build();
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		setPathWithinHandlerMapping(exchange, "foo.txt");
		this.handler.handle(exchange).block(TIMEOUT);

		assertEquals(HttpStatus.PARTIAL_CONTENT, exchange.getResponse().getStatusCode());
		assertTrue(exchange.getResponse().getHeaders().getContentType().toString()
				.startsWith("multipart/byteranges;boundary="));

		String boundary = "--" + exchange.getResponse().getHeaders().getContentType().toString().substring(30);

		Mono<DataBuffer> reduced = Flux.from(exchange.getResponse().getBody())
				.reduce(this.bufferFactory.allocateBuffer(), (previous, current) -> {
					previous.write(current);
					DataBufferUtils.release(current);
					return previous;
				});

		StepVerifier.create(reduced)
				.consumeNextWith(buf -> {
					String content = DataBufferTestUtils.dumpString(buf, StandardCharsets.UTF_8);
					String[] ranges = StringUtils.tokenizeToStringArray(content, "\r\n", false, true);

					assertEquals(boundary, ranges[0]);
					assertEquals("Content-Type: text/plain", ranges[1]);
					assertEquals("Content-Range: bytes 0-1/10", ranges[2]);
					assertEquals("So", ranges[3]);

					assertEquals(boundary, ranges[4]);
					assertEquals("Content-Type: text/plain", ranges[5]);
					assertEquals("Content-Range: bytes 4-5/10", ranges[6]);
					assertEquals(" t", ranges[7]);

					assertEquals(boundary, ranges[8]);
					assertEquals("Content-Type: text/plain", ranges[9]);
					assertEquals("Content-Range: bytes 8-9/10", ranges[10]);
					assertEquals("t.", ranges[11]);
				})
				.expectComplete()
				.verify();
	}
