	@Test
	public void applyFiltersAfterConfigurerAdded() {

		this.serverSpec.webFilter(new TestWebFilter("A"));

		this.serverSpec.apply(new MockServerConfigurer() {

			@Override
			public void afterConfigureAdded(WebTestClient.MockServerSpec<?> spec) {
				spec.webFilter(new TestWebFilter("B"));
			}
		});

		this.serverSpec.build().get().uri("/").exchange().expectBody(String.class)
				.isEqualTo("{test-attribute=:A:B}");
	}
