	@Test
	public void unresolvedExceptionWithWebHttpHandlerAdapter() throws Exception {

		// HttpWebHandlerAdapter handles unresolved errors

		new HttpWebHandlerAdapter(createWebHandler(new UnresolvedExceptionHandler()))
				.handle(this.exchange.getRequest(), this.exchange.getResponse()).block();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, this.exchange.getResponse().getStatusCode());
	}
