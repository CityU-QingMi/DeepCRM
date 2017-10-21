	@Test
	public void resolveSystemProperty() throws Exception {
		System.setProperty("systemProperty", "22");
		try {
			Mono<Object> mono = this.resolver.resolveArgument(
					this.paramSystemProperty,  new BindingContext(), this.exchange);

			Object value = mono.block();
			assertEquals(22, value);
		}
		finally {
			System.clearProperty("systemProperty");
		}

	}
