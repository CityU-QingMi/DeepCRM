	@Test
	public void testExceptionOnConfigParsingWithAmbiguousAdviceMethod() {
		try {
			new ClassPathXmlApplicationContext(getClass().getSimpleName() + "-ambiguous.xml", getClass());
		}
		catch (BeanCreationException ex) {
			Throwable cause = ex.getRootCause();
			assertTrue("Should be IllegalArgumentException", cause instanceof IllegalArgumentException);
			assertTrue("Cannot resolve method 'myBeforeAdvice' to a unique method",
					cause.getMessage().indexOf("Cannot resolve method 'myBeforeAdvice' to a unique method") != -1);
		}
	}
