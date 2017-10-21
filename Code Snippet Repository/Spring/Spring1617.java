	@Test
	public void byTypeAutowireWithExclusionInParentFactory() throws Exception {
		CountingFactory.reset();
		DefaultListableBeanFactory parent = getBeanFactory("autowire-with-exclusion.xml");
		parent.preInstantiateSingletons();
		DefaultListableBeanFactory child = new DefaultListableBeanFactory(parent);
		RootBeanDefinition robDef = new RootBeanDefinition(TestBean.class);
		robDef.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
		robDef.getPropertyValues().add("spouse", new RuntimeBeanReference("sally"));
		child.registerBeanDefinition("rob2", robDef);
		TestBean rob = (TestBean) child.getBean("rob2");
		assertEquals("props1", rob.getSomeProperties().getProperty("name"));
		assertEquals(1, CountingFactory.getFactoryBeanInstanceCount());
	}
