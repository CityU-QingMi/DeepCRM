	@Test
	public void setSessionIdWhenCustomHeaderNameThenSetsHeader() {
		String headerName = "x-auth";
		String id = "123";
		this.idResolver.setHeaderName(headerName);

		this.idResolver.setSessionId(this.exchange, id);

		assertEquals(Arrays.asList(id),
				this.exchange.getResponse().getHeaders().get(headerName));
	}
