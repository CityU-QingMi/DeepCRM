	@Test
	public void doesNotSupportParameter() {
		assertFalse(this.resolver.supportsParameter(this.stringParameter));
		try {
			this.resolver.supportsParameter(this.cookieMonoParameter);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"CookieValueMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}
