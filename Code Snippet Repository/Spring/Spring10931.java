	@Test
	public void handleErrorSignalWithMultipleHttpErrorHandlers() throws Exception {
		createWebHandler(
				new UnresolvedExceptionHandler(),
				new UnresolvedExceptionHandler(),
				new BadRequestExceptionHandler(),
				new UnresolvedExceptionHandler()).handle(this.exchange).block();

		assertEquals(HttpStatus.BAD_REQUEST, this.exchange.getResponse().getStatusCode());
	}
