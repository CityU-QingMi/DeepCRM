	@Test
	public void testTrustedFactoryMethod() throws Exception {
		try {
			beanFactory.getBean("privileged-static-factory-method");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getMostSpecificCause() instanceof SecurityException);
		}
	}
