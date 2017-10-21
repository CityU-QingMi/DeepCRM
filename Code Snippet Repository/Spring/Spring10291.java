	@Test
	public void zeroCopy() throws Exception {

		// Zero-copy only does not support servlet
		assumeTrue(server instanceof ReactorHttpServer || server instanceof UndertowHttpServer);

		RestTemplate restTemplate = new RestTemplate();

		RequestEntity<?> request =
				RequestEntity.get(new URI("http://localhost:" + port)).build();

		ResponseEntity<byte[]> response = restTemplate.exchange(request, byte[].class);

		Resource logo =
				new ClassPathResource("spring.png", ZeroCopyIntegrationTests.class);

		assertTrue(response.hasBody());
		assertEquals(logo.contentLength(), response.getHeaders().getContentLength());
		assertEquals(logo.contentLength(), response.getBody().length);
		assertEquals(MediaType.IMAGE_PNG, response.getHeaders().getContentType());

	}
