	@Test
	public void testCustomFactoryObject() throws Exception {
		try {
			beanFactory.getBean("spring-factory");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof SecurityException);
		}

	}
