	@Test
	public void doesNotSupport() throws Exception {
		assertFalse(this.resolver.supportsParameter(this.testMethod.arg(Mono.class, String.class)));
		assertFalse(this.resolver.supportsParameter(this.testMethod.arg(String.class)));
		try {
			this.resolver.supportsParameter(this.testMethod.arg(Mono.class, httpEntityType(String.class)));
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"HttpEntityArgumentResolver doesn't support reactive type wrapper"));
		}
	}
