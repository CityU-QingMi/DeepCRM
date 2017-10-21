	@Test
	public void notFoundGet() throws Exception {
		try {
			Future<?> future = template.execute(baseUrl + "/status/notfound", HttpMethod.GET, null, null);
			future.get();
			fail("HttpClientErrorException expected");
		}
		catch (ExecutionException ex) {
			assertTrue(ex.getCause() instanceof HttpClientErrorException);
			HttpClientErrorException cause = (HttpClientErrorException)ex.getCause();

			assertEquals(HttpStatus.NOT_FOUND, cause.getStatusCode());
			assertNotNull(cause.getStatusText());
			assertNotNull(cause.getResponseBodyAsString());
		}
	}
