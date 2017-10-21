	@Test
	public void doesNotSupport() throws Exception {
		assertFalse(this.resolver.supportsParameter(this.paramNotSupported));
		try {
			this.resolver.supportsParameter(this.paramAlsoNotSupported);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"ExpressionValueMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}
