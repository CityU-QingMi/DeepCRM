	@Test
	public void apply() {
		WebClient client = builder()
				.apply(builder -> builder.defaultHeader("Accept", "application/json").defaultCookie("id", "123"))
				.build();
		client.get().uri("/path").exchange();

		ClientRequest request = verifyExchange();
		assertEquals("application/json", request.headers().getFirst("Accept"));
		assertEquals("123", request.cookies().getFirst("id"));
		verifyNoMoreInteractions(this.exchangeFunction);
	}
