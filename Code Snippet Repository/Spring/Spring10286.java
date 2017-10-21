	@Test
	public void beforeCommitActionWithSetComplete() throws Exception {
		ResponseCookie cookie = ResponseCookie.from("ID", "123").build();
		TestServerHttpResponse response = new TestServerHttpResponse();
		response.beforeCommit(() -> {
			response.getCookies().add(cookie.getName(), cookie);
			return Mono.empty();
		});
		response.setComplete().block();

		assertTrue(response.statusCodeWritten);
		assertTrue(response.headersWritten);
		assertTrue(response.cookiesWritten);
		assertTrue(response.body.isEmpty());
		assertSame(cookie, response.getCookies().getFirst("ID"));
	}
