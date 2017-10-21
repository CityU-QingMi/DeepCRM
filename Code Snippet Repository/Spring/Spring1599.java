	@Test
	public void testSpringInitBean() throws Exception {
		try {
			beanFactory.getBean("spring-init");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof SecurityException);
		}
	}
