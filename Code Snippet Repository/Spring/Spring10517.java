	@Test
	public void notFound() {
		try {
			template.execute(baseUrl + "/status/notfound", HttpMethod.GET, null, null);
			fail("HttpClientErrorException expected");
		}
		catch (HttpClientErrorException ex) {
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
			assertNotNull(ex.getStatusText());
			assertNotNull(ex.getResponseBodyAsString());
		}
	}
