	@Test
	public void defaultHeaderAndCookieOverrides() throws Exception {
		WebClient client = builder().defaultHeader("Accept", "application/json").defaultCookie("id", "123").build();
		client.get().uri("/path").header("Accept", "application/xml").cookie("id", "456").exchange();

		ClientRequest request = verifyExchange();
		assertEquals("application/xml", request.headers().getFirst("Accept"));
		assertEquals("456", request.cookies().getFirst("id"));
		verifyNoMoreInteractions(this.exchangeFunction);
	}
