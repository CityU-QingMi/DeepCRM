	@Test
	public void supportsParameter() {
		assertTrue("Map parameter not supported", resolver.supportsParameter(paramMap));
		assertTrue("MultiValueMap parameter not supported", resolver.supportsParameter(paramMultiValueMap));
		assertTrue("HttpHeaders parameter not supported", resolver.supportsParameter(paramHttpHeaders));
		assertFalse("non-@RequestParam map supported", resolver.supportsParameter(paramUnsupported));
		try {
			this.resolver.supportsParameter(this.paramAlsoUnsupported);
			fail();
		}
		catch (IllegalStateException ex) {
			assertTrue("Unexpected error message:\n" + ex.getMessage(),
					ex.getMessage().startsWith(
							"RequestHeaderMapMethodArgumentResolver doesn't support reactive type wrapper"));
		}
	}
