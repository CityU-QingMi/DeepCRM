	@Test
	public void testNotNullConstraint() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("bvpp", new RootBeanDefinition(BeanValidationPostProcessor.class));
		ac.registerBeanDefinition("capp", new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class));
		ac.registerBeanDefinition("bean", new RootBeanDefinition(NotNullConstrainedBean.class));
		try {
			ac.refresh();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getRootCause().getMessage().contains("testBean"));
			assertTrue(ex.getRootCause().getMessage().contains("invalid"));
		}
		ac.close();
	}
