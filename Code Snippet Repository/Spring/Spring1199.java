	@Test
	public void testReregisterBeanDefinition() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		lbf.registerBeanDefinition("testBean", bd1);
		assertTrue(lbf.getBean("testBean") instanceof TestBean);
		RootBeanDefinition bd2 = new RootBeanDefinition(NestedTestBean.class);
		bd2.setScope(RootBeanDefinition.SCOPE_PROTOTYPE);
		lbf.registerBeanDefinition("testBean", bd2);
		assertTrue(lbf.getBean("testBean") instanceof NestedTestBean);
	}
