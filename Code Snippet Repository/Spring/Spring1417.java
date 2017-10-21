	@Test
	@SuppressWarnings("")
	public void postProcess() {
		beanFactory = new DefaultListableBeanFactory();
		BeanDefinition def = new RootBeanDefinition(MyDeprecatedBean.class);
		String beanName = "deprecated";
		beanFactory.registerBeanDefinition(beanName, def);

		warner = new MyDeprecatedBeanWarner();
		warner.postProcessBeanFactory(beanFactory);
		assertEquals(beanName, this.beanName);
		assertEquals(def, this.beanDefinition);

	}
