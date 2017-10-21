	@Test
	public void useLocallyConfiguredIdentity() throws Exception {

		this.webTestClient
				.mutateWith(identity("Giovanni"))
				.get().uri("/userIdentity")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo("Hello Giovanni!");
	}
