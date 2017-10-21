	@Test
	public void optionalPropertyWithValue() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setConversionService(new DefaultConversionService());
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(OptionalTestBean.class)
						.addPropertyValue("name", "${my.name}")
						.getBeanDefinition());

		MockEnvironment env = new MockEnvironment();
		env.setProperty("my.name", "myValue");

		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		ppc.setEnvironment(env);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.postProcessBeanFactory(bf);
		assertThat(bf.getBean(OptionalTestBean.class).getName(), equalTo(Optional.of("myValue")));
	}
