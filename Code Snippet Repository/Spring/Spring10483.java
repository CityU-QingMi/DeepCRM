	@Test
	public void exchangePost() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("MyHeader", "MyValue");
		requestHeaders.setContentType(MediaType.TEXT_PLAIN);
		HttpEntity<String> requestEntity = new HttpEntity<>(helloWorld, requestHeaders);
		Future<ResponseEntity<Void>> resultFuture =
				template.exchange(baseUrl + "/{method}", HttpMethod.POST, requestEntity, Void.class, "post");
		ResponseEntity<Void> result = resultFuture.get();
		assertEquals("Invalid location", new URI(baseUrl + "/post/1"),
				result.getHeaders().getLocation());
		assertFalse(result.hasBody());
	}
