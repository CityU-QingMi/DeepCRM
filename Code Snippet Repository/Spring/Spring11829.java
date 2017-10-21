	@Test
	public void redirect() throws Exception {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory() {

			@Override
			protected void prepareConnection(HttpURLConnection conn, String method) throws IOException {
				super.prepareConnection(conn, method);
				conn.setInstanceFollowRedirects(false);
			}
		};

		URI uri = new URI("http://localhost:" + this.port + "/redirect");
		RequestEntity<Void> request = RequestEntity.get(uri).accept(MediaType.ALL).build();
		ResponseEntity<Void> response = new RestTemplate(factory).exchange(request, Void.class);

		assertEquals(HttpStatus.SEE_OTHER, response.getStatusCode());
		assertEquals("/", response.getHeaders().getLocation().toString());
	}
