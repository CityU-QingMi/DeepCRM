	@Test
	public void testConstructor() throws Exception {
		try {
			beanFactory.getBean("constructor");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			// expected
			assertTrue(ex.getMostSpecificCause() instanceof SecurityException);
		}
	}
