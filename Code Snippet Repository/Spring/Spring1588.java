	@Test
	public void testCustomInstanceFactoryMethod() throws Exception {
		try {
			beanFactory.getBean("custom-factory-method");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMostSpecificCause() instanceof SecurityException);
		}
	}
