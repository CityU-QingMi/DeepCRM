	@Test
	public void testInvalidBeanNameReference() throws Exception {
		try {
			this.beanFactory.getBean("jumble2");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getCause() instanceof BeanDefinitionStoreException);
			assertTrue(ex.getCause().getMessage().contains("rod2"));
		}
	}
