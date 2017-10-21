	@Test
	public void testGetBeanByTypeWithPrimary() throws Exception {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd1 = new RootBeanDefinition(TestBean.class);
		bd1.setLazyInit(true);
		RootBeanDefinition bd2 = new RootBeanDefinition(TestBean.class);
		bd2.setPrimary(true);
		lbf.registerBeanDefinition("bd1", bd1);
		lbf.registerBeanDefinition("bd2", bd2);
		TestBean bean = lbf.getBean(TestBean.class);
		assertThat(bean.getBeanName(), equalTo("bd2"));
		assertFalse(lbf.containsSingleton("bd1"));
	}
