	@Test
	public void testBeanDefinitionOverridingNotAllowed() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.setAllowBeanDefinitionOverriding(false);
		lbf.registerBeanDefinition("test", new RootBeanDefinition(TestBean.class));
		try {
			lbf.registerBeanDefinition("test", new RootBeanDefinition(NestedTestBean.class));
			fail("Should have thrown BeanDefinitionStoreException");
		}
		catch (BeanDefinitionStoreException ex) {
			assertEquals("test", ex.getBeanName());
			// expected
		}
	}
