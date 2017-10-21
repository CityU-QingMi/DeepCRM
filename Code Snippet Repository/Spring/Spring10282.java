	@Test
	public void writeAndFlushWithFluxOfDefaultDataBuffer() throws Exception {
		TestServerHttpResponse response = new TestServerHttpResponse();
		Flux<Flux<DefaultDataBuffer>> flux = Flux.just(Flux.just(wrap("foo")));
		response.writeAndFlushWith(flux).block();

		assertTrue(response.statusCodeWritten);
		assertTrue(response.headersWritten);
		assertTrue(response.cookiesWritten);

		assertEquals(1, response.body.size());
		assertEquals("foo", new String(response.body.get(0).asByteBuffer().array(), StandardCharsets.UTF_8));
	}
