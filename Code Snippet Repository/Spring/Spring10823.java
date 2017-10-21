	@Test
	public void supportedParametersInDefaultResolutionMode() throws Exception {
		processor = new ModelAttributeMethodProcessor(true);

		// Only non-simple types, even if not annotated
		assertTrue(this.processor.supportsParameter(this.paramNamedValidModelAttr));
		assertTrue(this.processor.supportsParameter(this.paramErrors));
		assertTrue(this.processor.supportsParameter(this.paramModelAttr));
		assertTrue(this.processor.supportsParameter(this.paramNonSimpleType));

		assertFalse(this.processor.supportsParameter(this.paramInt));
	}
