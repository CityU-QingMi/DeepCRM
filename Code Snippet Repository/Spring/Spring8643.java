	@Test
	public void applyFiltersBeforeServerCreated() {

		this.serverSpec.webFilter(new TestWebFilter("App-A"));
		this.serverSpec.webFilter(new TestWebFilter("App-B"));

		this.serverSpec.apply(new MockServerConfigurer() {

			@Override
			public void beforeServerCreated(WebHttpHandlerBuilder builder) {
				builder.filters(filters -> {
					filters.add(0, new TestWebFilter("Fwk-A"));
					filters.add(1, new TestWebFilter("Fwk-B"));
				});
			}
		});

		this.serverSpec.build().get().uri("/").exchange().expectBody(String.class)
				.isEqualTo("{test-attribute=:Fwk-A:Fwk-B:App-A:App-B}");
	}
