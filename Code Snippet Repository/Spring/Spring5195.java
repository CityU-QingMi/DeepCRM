	@Test
	public void convertObjectToObjectUsingValueOfMethod() {
		ISBN.reset();
		assertEquals(new ISBN("123456789"), conversionService.convert("123456789", ISBN.class));

		assertEquals("valueOf() invocations", 1, ISBN.valueOfCount);
		// valueOf() invokes the constructor
		assertEquals("constructor invocations", 2, ISBN.constructorCount);
		assertEquals("toString() invocations", 0, ISBN.toStringCount);
	}
