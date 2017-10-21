	@Test
	public void actualRequestWithCorsRejected() throws Exception {
		try {
			performGet("/cors-restricted", this.headers, String.class);
			fail();
		}
		catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
		}
	}
