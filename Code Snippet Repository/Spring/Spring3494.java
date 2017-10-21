	@Test
	public void testContextWithInvalidValueType() throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {INVALID_VALUE_TYPE_CONTEXT}, false);
		try {
			context.refresh();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.contains(TypeMismatchException.class));
			assertTrue(ex.toString().contains("someMessageSource"));
			assertTrue(ex.toString().contains("useCodeAsDefaultMessage"));
			checkExceptionFromInvalidValueType(ex);
			checkExceptionFromInvalidValueType(new ExceptionInInitializerError(ex));
			assertFalse(context.isActive());
		}
	}
