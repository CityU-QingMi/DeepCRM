	@Test
	public void testingBindingWithProceedingJoinPoint() {
		try {
			AbstractAspectJAdvice.currentJoinPoint();
			fail("Needs to be bound by interceptor action");
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
