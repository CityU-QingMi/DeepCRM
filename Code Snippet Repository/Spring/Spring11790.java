	@Test
	public void supportsParameter() {
		assertTrue(this.resolver.supportsParameter(this.paramNamedString));
		assertFalse(this.resolver.supportsParameter(this.paramString));
		try {
			this.resolver.supportsParameter(this.paramMono);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"PathVariableMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}
