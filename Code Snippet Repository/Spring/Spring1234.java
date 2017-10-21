	@Test
	public void testAutowireBeanWithFactoryBeanByType() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(LazyInitFactory.class);
		lbf.registerBeanDefinition("factoryBean", bd);
		LazyInitFactory factoryBean = (LazyInitFactory) lbf.getBean("&factoryBean");
		assertNotNull("The FactoryBean should have been registered.", factoryBean);
		FactoryBeanDependentBean bean = (FactoryBeanDependentBean) lbf.autowire(FactoryBeanDependentBean.class,
				AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
		assertEquals("The FactoryBeanDependentBean should have been autowired 'by type' with the LazyInitFactory.",
				factoryBean, bean.getFactoryBean());
	}
