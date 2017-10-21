	@Test
	public void testHandlerNotFound() throws Exception {
		URI url = new URI("http://localhost:" + this.port + "/oops");
		RequestEntity<Void> request = RequestEntity.get(url).build();
		try {
			new RestTemplate().exchange(request, byte[].class);
		}
		catch (HttpClientErrorException ex) {
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}
