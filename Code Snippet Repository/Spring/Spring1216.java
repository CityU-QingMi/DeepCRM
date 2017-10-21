	@Test
	public void testDependsOnCycle() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setDependsOn("tb2");
		lbf.registerBeanDefinition("tb1", bd1);
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setDependsOn("tb1");
		lbf.registerBeanDefinition("tb2", bd2);
		try {
			lbf.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
			assertTrue(ex.getMessage().contains("Circular"));
			assertTrue(ex.getMessage().contains("'tb2'"));
			assertTrue(ex.getMessage().contains("'tb1'"));
		}
	}
