	@Test
	public void testBeanNameAutoProxyCreator() {
		StaticApplicationContext sac = new StaticApplicationContext();
		sac.registerSingleton("testInterceptor", TestInterceptor.class);

		RootBeanDefinition proxyCreator = new RootBeanDefinition(BeanNameAutoProxyCreator.class);
		proxyCreator.getPropertyValues().add("interceptorNames", "testInterceptor");
		proxyCreator.getPropertyValues().add("beanNames", "singletonToBeProxied,innerBean,singletonFactoryToBeProxied");
		sac.getDefaultListableBeanFactory().registerBeanDefinition("beanNameAutoProxyCreator", proxyCreator);

		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
		RootBeanDefinition innerBean = new RootBeanDefinition(TestBean.class);
		bd.getPropertyValues().add("spouse", new BeanDefinitionHolder(innerBean, "innerBean"));
		sac.getDefaultListableBeanFactory().registerBeanDefinition("singletonToBeProxied", bd);

		sac.registerSingleton("singletonFactoryToBeProxied", DummyFactory.class);
		sac.registerSingleton("autowiredIndexedTestBean", IndexedTestBean.class);

		sac.refresh();

		MessageSource messageSource = (MessageSource) sac.getBean("messageSource");
		ITestBean singletonToBeProxied = (ITestBean) sac.getBean("singletonToBeProxied");
		assertFalse(Proxy.isProxyClass(messageSource.getClass()));
		assertTrue(Proxy.isProxyClass(singletonToBeProxied.getClass()));
		assertTrue(Proxy.isProxyClass(singletonToBeProxied.getSpouse().getClass()));

		// test whether autowiring succeeded with auto proxy creation
		assertEquals(sac.getBean("autowiredIndexedTestBean"), singletonToBeProxied.getNestedIndexedBean());

		TestInterceptor ti = (TestInterceptor) sac.getBean("testInterceptor");
		// already 2: getSpouse + getNestedIndexedBean calls above
		assertEquals(2, ti.nrOfInvocations);
		singletonToBeProxied.getName();
		singletonToBeProxied.getSpouse().getName();
		assertEquals(5, ti.nrOfInvocations);

		ITestBean tb = (ITestBean) sac.getBean("singletonFactoryToBeProxied");
		assertTrue(AopUtils.isJdkDynamicProxy(tb));
		assertEquals(5, ti.nrOfInvocations);
		tb.getAge();
		assertEquals(6, ti.nrOfInvocations);

		ITestBean tb2 = (ITestBean) sac.getBean("singletonFactoryToBeProxied");
		assertSame(tb, tb2);
		assertEquals(6, ti.nrOfInvocations);
		tb2.getAge();
		assertEquals(7, ti.nrOfInvocations);
	}
