	@Test
	public void testCustomInitBean() throws Exception {
		try {
			beanFactory.getBean("custom-init");
			fail("expected security exception");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof SecurityException);
		}
	}
