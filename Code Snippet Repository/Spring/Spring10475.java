	@Test
	public void serverError() throws Exception {
		try {
			Future<Void> future = template.execute(baseUrl + "/status/server", HttpMethod.GET, null, null);
			future.get();
			fail("HttpServerErrorException expected");
		}
		catch (ExecutionException ex) {
			assertTrue(ex.getCause() instanceof HttpServerErrorException);
			HttpServerErrorException cause = (HttpServerErrorException)ex.getCause();

			assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, cause.getStatusCode());
			assertNotNull(cause.getStatusText());
			assertNotNull(cause.getResponseBodyAsString());
		}
	}
