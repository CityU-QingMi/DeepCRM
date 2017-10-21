	@Test
	public void accessingNullPropertyViaReflection_SPR5663() throws AccessException {
		PropertyAccessor propertyAccessor = new ReflectivePropertyAccessor();
		EvaluationContext context = TestScenarioCreator.getTestEvaluationContext();
		assertFalse(propertyAccessor.canRead(context, null, "abc"));
		assertFalse(propertyAccessor.canWrite(context, null, "abc"));
		try {
			propertyAccessor.read(context, null, "abc");
			fail("Should have failed with an IllegalStateException");
		}
		catch (IllegalStateException ae) {
			// success
		}
		try {
			propertyAccessor.write(context, null, "abc", "foo");
			fail("Should have failed with an AccessEIllegalStateExceptionxception");
		}
		catch (IllegalStateException ae) {
			// success
		}
	}
