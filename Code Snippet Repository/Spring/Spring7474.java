	@Test
	public void supportsReturnType() throws Exception {
		assertTrue(this.handler.supportsReturnType(this.sendToReturnType));
		assertTrue(this.handler.supportsReturnType(this.sendToUserReturnType));
		assertFalse(this.handler.supportsReturnType(this.noAnnotationsReturnType));
		assertTrue(this.handlerAnnotationNotRequired.supportsReturnType(this.noAnnotationsReturnType));

		assertTrue(this.handler.supportsReturnType(this.defaultNoAnnotation));
		assertTrue(this.handler.supportsReturnType(this.defaultEmptyAnnotation));
		assertTrue(this.handler.supportsReturnType(this.defaultOverrideAnnotation));

		assertTrue(this.handler.supportsReturnType(this.userDefaultNoAnnotation));
		assertTrue(this.handler.supportsReturnType(this.userDefaultEmptyAnnotation));
		assertTrue(this.handler.supportsReturnType(this.userDefaultOverrideAnnotation));
	}
