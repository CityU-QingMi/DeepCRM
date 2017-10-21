	@Test
	public void initMethod() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		new XmlBeanDefinitionReader(bf).loadBeanDefinitions(
				new ClassPathResource("NestedBeansElementAttributeRecursionTests-init-destroy-context.xml", this.getClass()));

		InitDestroyBean beanA = bf.getBean("beanA", InitDestroyBean.class);
		InitDestroyBean beanB = bf.getBean("beanB", InitDestroyBean.class);
		InitDestroyBean beanC = bf.getBean("beanC", InitDestroyBean.class);
		InitDestroyBean beanD = bf.getBean("beanD", InitDestroyBean.class);

		assertThat(beanA.initMethod1Called, is(true));
		assertThat(beanB.initMethod2Called, is(true));
		assertThat(beanC.initMethod3Called, is(true));
		assertThat(beanD.initMethod2Called, is(true));

		bf.destroySingletons();

		assertThat(beanA.destroyMethod1Called, is(true));
		assertThat(beanB.destroyMethod2Called, is(true));
		assertThat(beanC.destroyMethod3Called, is(true));
		assertThat(beanD.destroyMethod2Called, is(true));
	}
