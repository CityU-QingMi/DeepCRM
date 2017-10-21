	@Test
	@SuppressWarnings({ "", "" })
	public void exchangeGet() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("MyHeader", "MyValue");
		HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
		Future<ResponseEntity<String>> responseFuture =
				template.exchange(baseUrl + "/{method}", HttpMethod.GET, requestEntity, String.class, "get");
		ResponseEntity<String> response = responseFuture.get();
		assertEquals("Invalid content", helloWorld, response.getBody());
	}
