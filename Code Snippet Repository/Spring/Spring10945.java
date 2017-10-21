	@Test
	public void setSessionIdWhenMultipleThenSetsSingleHeader() {
		String id = "123";
		this.idResolver.setSessionId(this.exchange, "overriddenByNextInvocation");

		this.idResolver.setSessionId(this.exchange, id);

		assertEquals(Arrays.asList(id),
				this.exchange.getResponse().getHeaders().get(HeaderWebSessionIdResolver.DEFAULT_HEADER_NAME));
	}
