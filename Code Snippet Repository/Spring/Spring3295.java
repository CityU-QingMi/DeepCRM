	@Test
	public void findsBeansByTypeIfNotInstantiated() {
		AnnotationConfigApplicationContext bf = new AnnotationConfigApplicationContext();
		bf.registerBeanDefinition("fooConfig", new RootBeanDefinition(FooConfig.class));
		bf.getBeanFactory().addBeanPostProcessor(new PredictingBPP());
		bf.refresh();

		assertThat(bf.isTypeMatch("&foo", FactoryBean.class), is(true));

		@SuppressWarnings("rawtypes")
		Map<String, FactoryBean> fbBeans = bf.getBeansOfType(FactoryBean.class);
		assertThat(1, equalTo(fbBeans.size()));
		assertThat("&foo", equalTo(fbBeans.keySet().iterator().next()));

		Map<String, AnInterface> aiBeans = bf.getBeansOfType(AnInterface.class);
		assertThat(1, equalTo(aiBeans.size()));
		assertThat("&foo", equalTo(aiBeans.keySet().iterator().next()));
	}
