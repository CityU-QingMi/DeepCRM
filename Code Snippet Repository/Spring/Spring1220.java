	@Test
	public void testGetBeanByTypeWithMultiplePrimary() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setPrimary(true);
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setPrimary(true);
		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		thrown.expect(NoUniqueBeanDefinitionException.class);
		thrown.expectMessage(containsString("more than one 'primary'"));
		lbf.getBean(TestBean.class);
	}
