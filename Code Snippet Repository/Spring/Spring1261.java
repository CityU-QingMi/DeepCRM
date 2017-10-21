	@Test
	public void testBeanDefinitionWithInterface() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		lbf.registerBeanDefinition("test", new RootBeanDefinition(ITestBean.class));
		try {
			lbf.getBean("test");
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			assertEquals("test", ex.getBeanName());
			assertTrue(ex.getMessage().toLowerCase().contains("interface"));
		}
	}
