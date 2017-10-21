	@Test
	public void optionalPropertyWithoutValue() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setConversionService(new DefaultConversionService());
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(OptionalTestBean.class)
						.addPropertyValue("name", "${my.name}")
						.getBeanDefinition());

		MockEnvironment env = new MockEnvironment();
		env.setProperty("my.name", "");

		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();
		ppc.setEnvironment(env);
		ppc.setIgnoreUnresolvablePlaceholders(true);
		ppc.setNullValue("");
		ppc.postProcessBeanFactory(bf);
		assertThat(bf.getBean(OptionalTestBean.class).getName(), equalTo(Optional.empty()));
	}
