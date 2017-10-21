	@Test
	public void testSizeConstraint() {
		GenericApplicationContext ac = new GenericApplicationContext();
		ac.registerBeanDefinition("bvpp", new RootBeanDefinition(BeanValidationPostProcessor.class));
		RootBeanDefinition bd = new RootBeanDefinition(NotNullConstrainedBean.class);
		bd.getPropertyValues().add("testBean", new TestBean());
		bd.getPropertyValues().add("stringValue", "s");
		ac.registerBeanDefinition("bean", bd);
		try {
			ac.refresh();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertTrue(ex.getRootCause().getMessage().contains("stringValue"));
			assertTrue(ex.getRootCause().getMessage().contains("invalid"));
		}
		ac.close();
	}
