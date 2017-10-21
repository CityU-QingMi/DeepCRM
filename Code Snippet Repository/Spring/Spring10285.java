	@Test
	public void beforeCommitWithComplete() throws Exception {
		ResponseCookie cookie = ResponseCookie.from("ID", "123").build();
		TestServerHttpResponse response = new TestServerHttpResponse();
		response.beforeCommit(() -> {
			response.getCookies().add(cookie.getName(), cookie);
			return Mono.empty();
		});
		response.writeWith(Flux.just(wrap("a"), wrap("b"), wrap("c"))).block();

		assertTrue(response.statusCodeWritten);
		assertTrue(response.headersWritten);
		assertTrue(response.cookiesWritten);
		assertSame(cookie, response.getCookies().getFirst("ID"));

		assertEquals(3, response.body.size());
		assertEquals("a", new String(response.body.get(0).asByteBuffer().array(), StandardCharsets.UTF_8));
		assertEquals("b", new String(response.body.get(1).asByteBuffer().array(), StandardCharsets.UTF_8));
		assertEquals("c", new String(response.body.get(2).asByteBuffer().array(), StandardCharsets.UTF_8));
	}
