	@Test
	public void supportsParameter() throws Exception {
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(ServerWebExchange.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(ServerHttpRequest.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(ServerHttpResponse.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(HttpMethod.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(Locale.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(TimeZone.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(ZoneId.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(UriComponentsBuilder.class)));
		assertTrue(this.resolver.supportsParameter(this.testMethod.arg(UriBuilder.class)));

		assertFalse(this.resolver.supportsParameter(this.testMethod.arg(String.class)));
		try {
			this.resolver.supportsParameter(this.testMethod.arg(Mono.class, ServerWebExchange.class));
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"ServerWebExchangeArgumentResolver doesn't support reactive type wrapper"));
		}
	}
