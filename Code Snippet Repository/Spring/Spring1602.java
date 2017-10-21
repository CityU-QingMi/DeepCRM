	@Test
	public void testCustomStaticFactoryMethod() throws Exception {
		try {
			beanFactory.getBean("custom-static-factory-method");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMostSpecificCause() instanceof SecurityException);
		}
	}
