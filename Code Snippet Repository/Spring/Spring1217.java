	@Test
	public void testImplicitDependsOnCycle() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setDependsOn("tb2");
		lbf.registerBeanDefinition("tb1", bd1);
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setDependsOn("tb3");
		lbf.registerBeanDefinition("tb2", bd2);
		RootBeanDefinition bd3 = new RootBeanDefinition(TestBean.class);
		bd3.setDependsOn("tb1");
		lbf.registerBeanDefinition("tb3", bd3);
		try {
			lbf.preInstantiateSingletons();
			fail("Should have thrown BeanCreationException");
		}
		catch (BeanCreationException ex) {
			// expected
			assertTrue(ex.getMessage().contains("Circular"));
			assertTrue(ex.getMessage().contains("'tb3'"));
			assertTrue(ex.getMessage().contains("'tb1'"));
		}
	}
