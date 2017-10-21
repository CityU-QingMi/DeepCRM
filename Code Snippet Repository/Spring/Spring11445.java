	@Test
	public void basic() throws Exception {
		WebClient client = builder().build();
		client.get().uri("/path").exchange();

		ClientRequest request = verifyExchange();
		assertEquals("/base/path", request.url().toString());
		assertEquals(new HttpHeaders(), request.headers());
		assertEquals(Collections.emptyMap(), request.cookies());
	}
