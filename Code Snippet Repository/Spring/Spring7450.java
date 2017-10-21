	@Test
	public void supportsParameter() throws Exception {

		assertTrue(this.resolver.supportsParameter(this.paramAnnotated));
		assertTrue(this.resolver.supportsParameter(this.paramNotAnnotated));

		PayloadArgumentResolver strictResolver = new PayloadArgumentResolver(
				new StringMessageConverter(), testValidator(), false);

		assertTrue(strictResolver.supportsParameter(this.paramAnnotated));
		assertFalse(strictResolver.supportsParameter(this.paramNotAnnotated));
	}
