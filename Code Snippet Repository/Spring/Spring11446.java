	@Test
	public void requestHeaderAndCookie() throws Exception {
		WebClient client = builder().build();
		client.get().uri("/path").accept(MediaType.APPLICATION_JSON).cookie("id", "123").exchange();

		ClientRequest request = verifyExchange();
		assertEquals("application/json", request.headers().getFirst("Accept"));
		assertEquals("123", request.cookies().getFirst("id"));
		verifyNoMoreInteractions(this.exchangeFunction);
	}
